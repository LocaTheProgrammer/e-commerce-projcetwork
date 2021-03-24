package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.Articlolo_has_CarrelloRepository;
import it.karasho.dto.Articolo_has_CarrelloDTO;
import it.karasho.dto.Response;
import it.karasho.entity.Articolo_has_Carrello;

@Service
public class Articolo_has_CarrelloService {
	 @Autowired
	 private Articlolo_has_CarrelloRepository articolo_has_carrelloRepository;

	 
	 final static String error = "Nessun articolo_has_carrello trovato.";
		
		// create
		
			public Response<Articolo_has_Carrello> createArticolo_has_Carrello(Articolo_has_Carrello articolo_has_carrello) {

				Response<Articolo_has_Carrello> response = new Response<Articolo_has_Carrello>();

				try {
					this.articolo_has_carrelloRepository.save(articolo_has_carrello); 

					response.setResult(articolo_has_carrello);
					response.setResultTest(true);

				} catch (Exception e) {
					e.getStackTrace();
					response.setError("articolo_has_carrello non creato");

				}

				return response;

			}

			// delete
			public Response<String> deleteArticolo_has_CarrelloById(int id) {

				Response<String> response = new Response<String>();

				try {
					this.articolo_has_carrelloRepository.deleteById(id);

					response.setResult("articolo_has_carrello eliminato.");
					response.setResultTest(true);

				} catch (Exception e) {
					response.setError("articolo_has_carrello non eliminato correttamente.");
				}
				return response;
			}

			// findAll
			public Response<List<Articolo_has_CarrelloDTO>> findAllArticolo_has_Carrellos() {

				Response<List<Articolo_has_CarrelloDTO>> response = new Response<List<Articolo_has_CarrelloDTO>>();

				List<Articolo_has_CarrelloDTO> result = new ArrayList<>();

				try {

					Iterator<Articolo_has_Carrello> iterator = this.articolo_has_carrelloRepository.findAll().iterator();

					while (iterator.hasNext()) {

						Articolo_has_Carrello articolo_has_carrello = iterator.next();
						result.add(Articolo_has_CarrelloDTO.build(articolo_has_carrello));

					}

					response.setResult(result);
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//find articolo_has_carrello by id
			public Response<Articolo_has_CarrelloDTO> findArticolo_has_CarrelloById(int id) {

				Response<Articolo_has_CarrelloDTO> response = new Response<Articolo_has_CarrelloDTO>();

				try {

					Articolo_has_Carrello articolo_has_carrello = this.articolo_has_carrelloRepository.findById(id).get();

					response.setResult(Articolo_has_CarrelloDTO.build(articolo_has_carrello));
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}
			
			

			//update articolo_has_carrello
			public Response<Articolo_has_CarrelloDTO> updateArticolo_has_Carrello(int id, int idUtente, int idArticolo, int quantitaArticoli) {

				Response<Articolo_has_CarrelloDTO> response = new Response<Articolo_has_CarrelloDTO>();
				try {
					Articolo_has_Carrello articolo_has_carrello = this.articolo_has_carrelloRepository.findById(id).get();

					if (idUtente+"" != null)
						articolo_has_carrello.setIdUtente(idUtente);
					
					if (idArticolo+"" != null)
						articolo_has_carrello.setIdArticolo(idArticolo);
					if (quantitaArticoli+"" != null)
						articolo_has_carrello.setQuantitaArticoli(quantitaArticoli);
					
					
					this.articolo_has_carrelloRepository.save(articolo_has_carrello);
					
					response.setResult(Articolo_has_CarrelloDTO.build(articolo_has_carrello));
					response.setResultTest(true);

				} catch (Exception e) {
					
					response.setError(error);
					
				}	

				return response;
			}
			
}
