package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.CarrelloRepository;
import it.karasho.dto.CarrelloDTO;
import it.karasho.dto.Response;
import it.karasho.entity.Carrello;

@Service
public class CarrelloService {

	@Autowired
	private CarrelloRepository carrelloRepository;
	
final static String error = "Nessun carrello trovato.";
	
	// create
	
		public Response<Carrello> createCarrello(Carrello carrello) {

			Response<Carrello> response = new Response<Carrello>();

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
		public Response<String> deleteCarrelloById(int id) {

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
		public Response<CarrelloDTO> updateCarrello(int id, int idArticolo, int quantita, int idUtente) {

			Response<CarrelloDTO> response = new Response<CarrelloDTO>();
			try {
				Carrello carrello = this.carrelloRepository.findById(id).get();

				if (idArticolo+"" != null)
					carrello.setIdArticolo(idArticolo);
				
				if (quantita+"" != null)
					carrello.setQuantita(quantita);

				if (idUtente+"" != null)
					carrello.setIdUtente(idUtente);
				
				
				this.carrelloRepository.save(carrello);
				
				response.setResult(CarrelloDTO.build(carrello));
				response.setResultTest(true);

			} catch (Exception e) {
				
				response.setError(error);
				
			}	

			return response;
		}
		
}
