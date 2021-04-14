package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.CarrelloRepository;
import it.karasho.dto.CarrelloDTO;
import it.karasho.dto.Response;
import it.karasho.entity.Carrello;
import it.karasho.entity.CarrelloTotale;

@Service
public class CarrelloService {

	@Autowired
	private CarrelloRepository carrelloRepository;
	
	@Autowired
	private CarrelloTotaleService carrelloTotaleService;
	
	private static Logger log = LoggerFactory.getLogger(CarrelloService.class);
	
	final static String error = "Nessun carrello trovato.";
	
	// create
	
		public Response<Carrello> createCarrello(Carrello carrello) {
			
			Response<Carrello> response = new Response<Carrello>();

			try {
				log.info("email utente: "+carrello.getEmailUtente());
				this.carrelloRepository.save(carrello); 
				this.findCarrelloByEmail(carrello.getEmailUtente());

				response.setResult(carrello);
				response.setResultTest(true);

			} catch (Exception e) {
				e.getStackTrace();
				response.setError("carrello non creato");

			}

			return response;

		}

		// delete
		public Response<String> deleteCarrelloById(int id, String email) {

			Response<String> response = new Response<String>();

			try {
				this.carrelloRepository.deleteById(id);
				
				this.findCarrelloByEmailDelete(email);
				response.setResult("carrello eliminato.");
				response.setResultTest(true);

			} catch (Exception e) {
				response.setError("carrello non eliminato correttamente.");
			}
			return response;
		}
		
		// delete by email
				public Response<String> deleteCarrelloByEmail(String email) {

					Response<String> response = new Response<String>();

					try {
						Response<List<CarrelloDTO>> c=this.findJustAllCarrellosByEmail(email);
						for(int i=0;i<c.getResult().size();i++) {
							
							this.carrelloRepository.deleteById(c.getResult().get(i).getId());
						}
						this.carrelloTotaleService.updateCarrelloTotale(email, 0);
						response.setResult("carrello eliminato.");
						response.setResultTest(true);

					} catch (Exception e) {
						response.setError("carrello non eliminato correttamente.");
					}
					return response;
				}

		// findAll
		public Response<List<CarrelloDTO>> findAllCarrellos() {

			Response<List<CarrelloDTO>> response = new Response<List<CarrelloDTO>>();

			List<CarrelloDTO> result = new ArrayList<>();

			try {

				Iterator<Carrello> iterator = this.carrelloRepository.findAll().iterator();

				while (iterator.hasNext()) {

					Carrello carrello = iterator.next();
					result.add(CarrelloDTO.build(carrello));

				}

				response.setResult(result);
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}

			return response;

		}
		
		
		// findAll
				public Response<List<CarrelloDTO>> findJustAllCarrellosByEmail(String email) {

					Response<List<CarrelloDTO>> response = new Response<List<CarrelloDTO>>();

					List<CarrelloDTO> result = new ArrayList<>();

					try {

						Iterator<Carrello> iterator = this.carrelloRepository.findAll().iterator();

						while (iterator.hasNext()) {

							Carrello carrello = iterator.next();
							if(carrello.getEmailUtente().equals(email)) {								
								result.add(CarrelloDTO.build(carrello));
							}

						}

						response.setResult(result);
						response.setResultTest(true);

					} catch (Exception e) {

						response.setError(error);

					}

					return response;

				}
		
		public Response<List<CarrelloDTO>> findCarrelloByEmail(String email) {
			log.info("entro nel metodo findCarrelloByEmail");
			CarrelloTotale carrelloTotale=new CarrelloTotale();
			
			Response<List<CarrelloDTO>> response = new Response<List<CarrelloDTO>>();

			List<CarrelloDTO> result = new ArrayList<>();
			double totale=0;
			try {

				Iterator<Carrello> iterator = this.carrelloRepository.findAll().iterator();

				while (iterator.hasNext()) {
					
					Carrello carrello = iterator.next();
					if(carrello.getEmailUtente().equals(email)) {
						log.info("trovato il carrello per l'utente: "+email);
						totale+=carrello.getPrezzo()*carrello.getQuantita();
						carrelloTotale.setEmailUtente(email);
						carrelloTotale.setTotale(totale);
						//devo fare un if per controllare se il carrello totale esiste gia
						//se esiste lo updato se no lo creo
						if(!carrelloTotaleService.checkIfCarrelloTotaleExists(email)) {
							carrelloTotaleService.createCarrelloTotale(carrelloTotale);
						}else {							
							carrelloTotaleService.updateCarrelloTotale(email, totale);
						}
						result.add(CarrelloDTO.build(carrello));
					}else {
						this.carrelloTotaleService.updateCarrelloTotale(email, 0);
					}

				}

				response.setResult(result);
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}

			return response;

		}
		
	
		
		public Response<List<CarrelloDTO>> findCarrelloByEmailDelete(String email) {
			log.info("entro nel metodo findCarrelloByEmail DELETE");
			CarrelloTotale carrelloTotale=new CarrelloTotale();
			
			Response<List<CarrelloDTO>> response = new Response<List<CarrelloDTO>>();

			List<CarrelloDTO> result = new ArrayList<>();
			double totale=0;
			try {

				Iterator<Carrello> iterator = this.carrelloRepository.findAll().iterator();
				if(iterator.hasNext()) {
					
					while (iterator.hasNext()) {
						
						Carrello carrello = iterator.next();
						if(carrello.getEmailUtente().equals(email)) {
							log.info("trovato il carrello per l'utente: "+email);
							
							//devo fare un if per controllare se il carrello totale esiste gia
							//se esiste lo updato se no lo creo
							if(!carrelloTotaleService.checkIfCarrelloTotaleExists(email)) {
								carrelloTotaleService.updateCarrelloTotale(email, 0);
								result.add(CarrelloDTO.build(carrello));
							}else {
								totale+=carrello.getPrezzo()*carrello.getQuantita();
								carrelloTotale.setEmailUtente(email);
								carrelloTotale.setTotale(totale);
								carrelloTotaleService.updateCarrelloTotale(email, totale);
								result.add(CarrelloDTO.build(carrello));
							}
							
						}else {
							log.info("update carrello totale nel DELETE: "+ email+" 0");
							this.carrelloTotaleService.updateCarrelloTotale(email, 0);
						}
						
					}
				}else {
					log.info("update carrello totale nel DELETE ELSE : "+ email+" 0");
					this.carrelloTotaleService.updateCarrelloTotale(email, 0);
				}

				response.setResult(result);
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}

			return response;

		}
		

		//find carrello by id
		public Response<CarrelloDTO> findCarrelloById(int id) {

			Response<CarrelloDTO> response = new Response<CarrelloDTO>();

			try {

				Carrello carrello = this.carrelloRepository.findById(id).get();

				response.setResult(CarrelloDTO.build(carrello));
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}

			return response;

		}

		//update carrello
		public Response<CarrelloDTO> updateCarrello(int id, int idArticolo, int quantita, String emailUtente) {

			Response<CarrelloDTO> response = new Response<CarrelloDTO>();
			try {
				Carrello carrello = this.carrelloRepository.findById(id).get();

				if (idArticolo+"" != null)
					carrello.setIdArticolo(idArticolo);
				
				if (quantita+"" != null)
					carrello.setQuantita(quantita);

				if (emailUtente!= null)
					carrello.setEmailUtente(emailUtente);;
				
				
				this.carrelloRepository.save(carrello);
				
				response.setResult(CarrelloDTO.build(carrello));
				response.setResultTest(true);

			} catch (Exception e) {
				
				response.setError(error);
				
			}	

			return response;
		}
		
}
