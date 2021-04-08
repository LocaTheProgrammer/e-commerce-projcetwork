package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.SpedizioneRepository;
import it.karasho.dto.Response;
import it.karasho.dto.SpedizioneDTO;
import it.karasho.entity.Spedizione;



@Service
public class SpedizioneService {
	private static Logger log = LoggerFactory.getLogger(SpedizioneService.class);
	@Autowired
	private SpedizioneRepository spedizioneRepository;
	@Autowired
	private CarrelloService carrelloService;
	
	 final static String error = "Nessun spedizione trovato.";
		
		// create
		
			public Response<Spedizione> createSpedizione(Spedizione spedizione) {
				
				
				
				Response<Spedizione> response = new Response<Spedizione>();

				try {
					
						
						this.spedizioneRepository.save(spedizione);
						this.carrelloService.deleteCarrelloByEmail(spedizione.getEmailUtente());
						response.setResult(spedizione);
						response.setResultTest(true);
					

				} catch (Exception e) {
					e.getStackTrace();
					response.setError("spedizione non creato");

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
			
			// findAll
//						public boolean checkEmail(String email) {
//							
//							boolean b=false;
//							Response<List<SpedizioneDTO>> response = new Response<List<SpedizioneDTO>>();
//
//							List<SpedizioneDTO> result = new ArrayList<>();
//
//							try {
//
//								Iterator<Spedizione> iterator = this.spedizioneRepository.findAll().iterator();
//
//								while (iterator.hasNext()) {
//
//									Spedizione spedizione = iterator.next();
//									if(spedizione.getEmail().equals(email))
//										b=true;
//									
//
//								}
//
//								response.setResult(result);
//								response.setResultTest(true);
//
//							} catch (Exception e) {
//
//								response.setError(error);
//
//							}
//
//							return b;
//
//						}
			
//			public Response<SpedizioneDTO> loginSpedizione(String email, String password) {
//
//				Response<SpedizioneDTO> response = new Response<SpedizioneDTO>();
//
//				try {
//
//					Spedizione spedizione = this.spedizioneRepository.findByEmail(email);
//					
//					if(spedizione.getPassword().equals(password)) {						
//						response.setResult(SpedizioneDTO.build(spedizione));
//						response.setResultTest(true);
//					}
//
//				} catch (Exception e) {
//
//					response.setError(error);
//
//				}
//
//				return response;
//
//			}

			//update spedizione
//			public Response<SpedizioneDTO> updateSpedizione(int id, String nome, String cognome, String dataNascita, String email, String password) {
//
//				Response<SpedizioneDTO> response = new Response<SpedizioneDTO>();
//				try {
//					Spedizione spedizione = this.spedizioneRepository.findById(id).get();
//
//					if (nome != null)
//						spedizione.setNome(nome);
//					
//					if (cognome != null)
//						spedizione.setCognome(cognome);
//					
//					if (dataNascita != null)
//						spedizione.setDataNascita(dataNascita);
//					
//					if (email != null)
//						spedizione.setEmail(email);
//					
//					if (password != null)
//						spedizione.setPassword(password);
//					
//					this.spedizioneRepository.save(spedizione);
//					
//					response.setResult(SpedizioneDTO.build(spedizione));
//					response.setResultTest(true);
//
//				} catch (Exception e) {
//					
//					response.setError(error);
//					
//				}	
//
//				return response;
//			}
}
