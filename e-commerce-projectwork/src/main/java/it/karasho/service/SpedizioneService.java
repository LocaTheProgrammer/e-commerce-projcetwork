package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.SpedizioneRepository;
import it.karasho.dto.Response;
import it.karasho.dto.SpedizioneDTO;

import it.karasho.entity.Spedizione;



@Service
public class SpedizioneService {
	//private static Logger log = LoggerFactory.getLogger(SpedizioneService.class);
	@Autowired
	private SpedizioneRepository spedizioneRepository;
	@Autowired
	private CarrelloService carrelloService;
	@Autowired 
	private MagazzinoService magazzinoService;
	
	 final static String error = "Nessun spedizione trovato.";
		
		// create
		
			public Response<Spedizione> createSpedizione(Spedizione spedizione) {
				
				
				//if(magazzinoService.checIfIsPreorderById())
				Response<Spedizione> response = new Response<Spedizione>();

				try {
					
						String[] a=spedizione.getIdArticoli().split(";");
						String[] q=spedizione.getQuantita().split(";");
						this.spedizioneRepository.save(spedizione);
						
						for(int i=0;i<a.length;i++) {
							if(magazzinoService.checIfIsPreorderById(Integer.parseInt(a[i]))) {
								magazzinoService.updateMagazzino(Integer.parseInt(a[i]), Integer.parseInt(a[i]), 0, Integer.parseInt(q[i]));
								carrelloService.deleteCarrelloByEmail(spedizione.getEmailUtente());
							}else {
								if(magazzinoService.findMagazzinoById(Integer.parseInt(a[i])).getResult().getDisponibilita()-Integer.parseInt(q[i])<0) {
									magazzinoService.updateMagazzino(Integer.parseInt(a[i]), Integer.parseInt(a[i]), 0, Integer.parseInt(q[i]));
									carrelloService.deleteCarrelloByEmail(spedizione.getEmailUtente());
								}else {									
									magazzinoService.updateMagazzino(Integer.parseInt(a[i]), Integer.parseInt(a[i]), magazzinoService.findMagazzinoById(Integer.parseInt(a[i])).getResult().getDisponibilita()-Integer.parseInt(q[i]), magazzinoService.findMagazzinoById(Integer.parseInt(a[i])).getResult().getPreorder());
									carrelloService.deleteCarrelloByEmail(spedizione.getEmailUtente());
								}
							}
							
						}
						
						carrelloService.deleteCarrelloByEmail(spedizione.getEmailUtente());
						response.setResult(spedizione);
						response.setResultTest(true);
					

				} catch (Exception e) {
					e.getStackTrace();
					response.setError("spedizione non creato");
					carrelloService.deleteCarrelloByEmail(spedizione.getEmailUtente());

				}

				return response;

			}

			// delete
			
			public Response<String> deleteSpedizioneById(int id) {

				Response<String> response = new Response<String>();

				try {
					this.spedizioneRepository.deleteById(id);

					response.setResult("spedizione eliminato.");
					response.setResultTest(true);

				} catch (Exception e) {
					response.setError("spedizione non eliminato correttamente.");
				}
				return response;
			}

			// findAll
			public Response<List<SpedizioneDTO>> findAllSpediziones() {

				Response<List<SpedizioneDTO>> response = new Response<List<SpedizioneDTO>>();

				List<SpedizioneDTO> result = new ArrayList<>();

				try {

					Iterator<Spedizione> iterator = this.spedizioneRepository.findAll().iterator();

					while (iterator.hasNext()) {

						Spedizione spedizione = iterator.next();
						result.add(SpedizioneDTO.build(spedizione));

					}

					response.setResult(result);
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//find spedizione by id
			public Response<SpedizioneDTO> findSpedizioneById(int id) {

				Response<SpedizioneDTO> response = new Response<SpedizioneDTO>();

				try {

					Spedizione spedizione = this.spedizioneRepository.findById(id).get();

					response.setResult(SpedizioneDTO.build(spedizione));
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}
			

}
