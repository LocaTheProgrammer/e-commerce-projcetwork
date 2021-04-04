package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	final static String error = "Nessun carrello trovato.";
	
	// create
	
		public Response<CarrelloTotale> createCarrelloTotale(CarrelloTotale carrello) {

			Response<CarrelloTotale> response = new Response<CarrelloTotale>();

			try {
				this.carrelloRepository.save(carrello); 

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

		//find carrello by id
		public Response<CarrelloTotaleDTO> findCarrelloTotaleById(int id) {

			Response<CarrelloTotaleDTO> response = new Response<CarrelloTotaleDTO>();

			try {

				CarrelloTotale carrello = this.carrelloRepository.findById(id).get();

				response.setResult(CarrelloTotaleDTO.build(carrello));
				response.setResultTest(true);

			} catch (Exception e) {

				response.setError(error);

			}

			return response;

		}

//		//update carrello
//		public Response<CarrelloTotaleDTO> updateCarrelloTotale(int id, int idArticolo, int quantita, String emailUtente) {
//
//			Response<CarrelloTotaleDTO> response = new Response<CarrelloTotaleDTO>();
//			try {
//				CarrelloTotale carrello = this.carrelloRepository.findById(id).get();
//
//				if (idArticolo+"" != null)
//					carrello.setIdArticolo(idArticolo);
//				
//				if (quantita+"" != null)
//					carrello.setQuantita(quantita);
//
//				if (emailUtente!= null)
//					carrello.setEmailUtente(emailUtente);;
//				
//				
//				this.carrelloRepository.save(carrello);
//				
//				response.setResult(CarrelloTotaleDTO.build(carrello));
//				response.setResultTest(true);
//
//			} catch (Exception e) {
//				
//				response.setError(error);
//				
//			}	
//
//			return response;
//		}
		
}
