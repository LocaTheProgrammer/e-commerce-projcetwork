package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.ArticoloRepository;
import it.karasho.dto.ArticoloDTO;
import it.karasho.dto.Response;
import it.karasho.entity.Articolo;

@Service
public class ArticoloService {
	
	 @Autowired
	 private ArticoloRepository articoloRepository;

	 
	 final static String error = "Nessun articolo trovato.";
		
		// create
		
			public Response<Articolo> createArticolo(Articolo articolo) {

				Response<Articolo> response = new Response<Articolo>();

				try {
					this.articoloRepository.save(articolo); 

					response.setResult(articolo);
					response.setResultTest(true);

				} catch (Exception e) {
					e.getStackTrace();
					response.setError("articolo non creato");

				}

				return response;

			}

			// delete
			public Response<String> deleteArticoloById(int id) {

				Response<String> response = new Response<String>();

				try {
					this.articoloRepository.deleteById(id);

					response.setResult("articolo eliminato.");
					response.setResultTest(true);

				} catch (Exception e) {
					response.setError("articolo non eliminato correttamente.");
				}
				return response;
			}

			// findAll
			public Response<List<ArticoloDTO>> findAllArticolos() {

				Response<List<ArticoloDTO>> response = new Response<List<ArticoloDTO>>();

				List<ArticoloDTO> result = new ArrayList<>();

				try {

					Iterator<Articolo> iterator = this.articoloRepository.findAll().iterator();

					while (iterator.hasNext()) {

						Articolo articolo = iterator.next();
						result.add(ArticoloDTO.build(articolo));

					}

					response.setResult(result);
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//find articolo by id
			public Response<ArticoloDTO> findArticoloById(int id) {

				Response<ArticoloDTO> response = new Response<ArticoloDTO>();

				try {

					Articolo articolo = this.articoloRepository.findById(id).get();

					response.setResult(ArticoloDTO.build(articolo));
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//update articolo
			public Response<ArticoloDTO> updateArticolo(int id, String nome, String descrizione, double prezzo) {

				Response<ArticoloDTO> response = new Response<ArticoloDTO>();
				try {
					Articolo articolo = this.articoloRepository.findById(id).get();

					if (nome != null)
						articolo.setNome(nome);
					
					if (descrizione != null)
						articolo.setDescrizione(descrizione);
					if (prezzo+"" != null)
						articolo.setPrezzo(prezzo);
					
					
					this.articoloRepository.save(articolo);
					
					response.setResult(ArticoloDTO.build(articolo));
					response.setResultTest(true);

				} catch (Exception e) {
					
					response.setError(error);
					
				}	

				return response;
			}
			
}
