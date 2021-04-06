package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.karasho.dao.CarrelloTotaleRepository;
import it.karasho.dto.CarrelloTotaleDTO;
import it.karasho.dto.Response;
import it.karasho.entity.CarrelloTotale;

@Service
public class CarrelloTotaleService {
	@Autowired
	private CarrelloTotaleRepository carrelloRepository;
	private static Logger log = LoggerFactory.getLogger(CarrelloTotaleService.class);
	final static String error = "Nessun carrello trovato.";
	
	// create
	
		public Response<CarrelloTotale> createCarrelloTotale(CarrelloTotale carrello) {

			Response<CarrelloTotale> response = new Response<CarrelloTotale>();

			try {
				this.carrelloRepository.save(carrello); 
				log.info("creazione carrello totale");
				response.setResult(carrello);
				response.setResultTest(true);

			} catch (Exception e) {
				e.getStackTrace();
				response.setError("carrello non creato");

			}

			return response;

		}

		// delete
		public Response<String> deleteCarrelloTotaleById(int id) {

			Response<String> response = new Response<String>();

			try {
				this.carrelloRepository.deleteById(id);

				response.setResult("carrello eliminato.");
				response.setResultTest(true);

			} catch (Exception e) {
				response.setError("carrello non eliminato correttamente.");
			}
			return response;
		}

		// findAll
		public Response<List<CarrelloTotaleDTO>> findAllCarrelloTotales() {

			Response<List<CarrelloTotaleDTO>> response = new Response<List<CarrelloTotaleDTO>>();

			List<CarrelloTotaleDTO> result = new ArrayList<>();

			try {

				Iterator<CarrelloTotale> iterator = this.carrelloRepository.findAll().iterator();

				while (iterator.hasNext()) {

					CarrelloTotale carrello = iterator.next();
					result.add(CarrelloTotaleDTO.build(carrello));

				}

				response.setResult(result);
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}

			return response;

		}
		
		// findLast
//				public Response<CarrelloTotaleDTO> findLastTotale(String email) {
//
//					Response<List<CarrelloTotaleDTO>> response = new Response<List<CarrelloTotaleDTO>>();
//				
//					int last=0;
//					try {
//
//						Iterator<CarrelloTotale> iterator = this.carrelloRepository.findAll().iterator();
//
//						while (iterator.hasNext()) {
//
//							CarrelloTotale ct = iterator.next();
//							
//							if(!iterator.hasNext()) {						
//								
//								last=ct.getId();
//							}
//
//						}
//						
//
//
//					} catch (Exception e) {
//
//						response.setError(error);
//
//					}
//					
//					log.info("passaggio prima del return\nid last: "+last+"\n\n\n\n\n");
//					return findCarrelloTotaleById(last);
//
//				}
//		
//		//find carrello by id
//		public Response<CarrelloTotaleDTO> findCarrelloTotaleById(int id) {
//
//			Response<CarrelloTotaleDTO> response = new Response<CarrelloTotaleDTO>();
//
//			try {
//
//				CarrelloTotale carrello = this.carrelloRepository.findById(id).get();
//
//				response.setResult(CarrelloTotaleDTO.build(carrello));
//				response.setResultTest(true);
//
//			} catch (Exception e) {
//
//				response.setError(error);
//
//			}
//			log.info("resposne find last: "+response);
//			return response;
//
//		}
		
		public Response<CarrelloTotaleDTO> findTotaleByEmail(String email) {

			Response<CarrelloTotaleDTO> response = new Response<CarrelloTotaleDTO>();

			try {

				CarrelloTotale carrello = this.carrelloRepository.findByEmailUtente(email);

				response.setResult(CarrelloTotaleDTO.build(carrello));
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}
			
			return response;

		}
		
		public boolean checkIfCarrelloTotaleExists(String email) {
			boolean found = false;
			Response<List<CarrelloTotaleDTO>> response = new Response<List<CarrelloTotaleDTO>>();

			List<CarrelloTotaleDTO> result = new ArrayList<>();

			try {

				Iterator<CarrelloTotale> iterator = this.carrelloRepository.findAll().iterator();

				while (iterator.hasNext()) {
					
					CarrelloTotale carrello = iterator.next();
					if(carrello.getEmailUtente().equals(email)) {
						found=true;
						response.setResult(result);
						response.setResultTest(true);
					}

				}


			} catch (Exception e) {

				response.setError(error);

			}

			return found;


		}

		//update carrello
		public Response<CarrelloTotaleDTO> updateCarrelloTotale(String emailUtente, double totale) {

			Response<CarrelloTotaleDTO> response = new Response<CarrelloTotaleDTO>();
			try {
				CarrelloTotale carrello = this.carrelloRepository.findByEmailUtente(emailUtente);

				carrello.setEmailUtente(emailUtente);
				carrello.setTotale(totale);
				
				this.carrelloRepository.save(carrello);
				
				response.setResult(CarrelloTotaleDTO.build(carrello));
				response.setResultTest(true);

			} catch (Exception e) {
				
				response.setError(error);
				
			}	

			return response;
		}
		
}
