package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.MagazzinoRepository;
import it.karasho.dto.MagazzinoDTO;
import it.karasho.dto.Response;
import it.karasho.entity.Magazzino;

@Service
public class MagazzinoService {

	
	@Autowired
	private MagazzinoRepository magazzinoRepository;
	
	
	 final static String error = "Nessun magazzino trovato.";
		
		// create
		
			public Response<Magazzino> createMagazzino(Magazzino magazzino) {

				Response<Magazzino> response = new Response<Magazzino>();

				try {
					this.magazzinoRepository.save(magazzino); 

					response.setResult(magazzino);
					response.setResultTest(true);

				} catch (Exception e) {
					e.getStackTrace();
					response.setError("magazzino non creato");

				}

				return response;

			}

			// delete
			public Response<String> deleteMagazzinoById(int id) {

				Response<String> response = new Response<String>();

				try {
					this.magazzinoRepository.deleteById(id);

					response.setResult("magazzino eliminato.");
					response.setResultTest(true);

				} catch (Exception e) {
					response.setError("magazzino non eliminato correttamente.");
				}
				return response;
			}

			// findAll
			public Response<List<MagazzinoDTO>> findAllMagazzinos() {

				Response<List<MagazzinoDTO>> response = new Response<List<MagazzinoDTO>>();

				List<MagazzinoDTO> result = new ArrayList<>();

				try {

					Iterator<Magazzino> iterator = this.magazzinoRepository.findAll().iterator();

					while (iterator.hasNext()) {

						Magazzino magazzino = iterator.next();
						result.add(MagazzinoDTO.build(magazzino));

					}

					response.setResult(result);
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//find magazzino by id
			public Response<MagazzinoDTO> findMagazzinoById(int id) {

				Response<MagazzinoDTO> response = new Response<MagazzinoDTO>();

				try {

					Magazzino magazzino = this.magazzinoRepository.findById(id).get();

					response.setResult(MagazzinoDTO.build(magazzino));
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//update magazzino
			public Response<MagazzinoDTO> updateMagazzino(int id, int idArticolo, int disponibilita) {

				Response<MagazzinoDTO> response = new Response<MagazzinoDTO>();
				try {
					Magazzino magazzino = this.magazzinoRepository.findById(id).get();

					if (idArticolo+"" != null)
						magazzino.setIdArticolo(idArticolo);
					
					if (disponibilita+"" != null)
						magazzino.setDisponibilita(disponibilita);
					
					
					this.magazzinoRepository.save(magazzino);
					
					response.setResult(MagazzinoDTO.build(magazzino));
					response.setResultTest(true);

				} catch (Exception e) {
					
					response.setError(error);
					
				}	

				return response;
			}
	
}
