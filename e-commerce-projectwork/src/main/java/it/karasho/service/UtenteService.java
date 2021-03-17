package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.UtenteRepository;
import it.karasho.dto.Response;
import it.karasho.dto.UtenteDTO;
import it.karasho.entity.Utente;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository userRepository;
	
	 final static String error = "Nessun user trovato.";
		
		// create
		
			public Response<Utente> createUtente(Utente user) {

				Response<Utente> response = new Response<Utente>();

				try {
					this.userRepository.save(user); 

					response.setResult(user);
					response.setResultTest(true);

				} catch (Exception e) {
					e.getStackTrace();
					response.setError("user non creato");

				}

				return response;

			}

			// delete
			public Response<String> deleteUtenteById(int id) {

				Response<String> response = new Response<String>();

				try {
					this.userRepository.deleteById(id);

					response.setResult("user eliminato.");
					response.setResultTest(true);

				} catch (Exception e) {
					response.setError("user non eliminato correttamente.");
				}
				return response;
			}

			// findAll
			public Response<List<UtenteDTO>> findAllUtentes() {

				Response<List<UtenteDTO>> response = new Response<List<UtenteDTO>>();

				List<UtenteDTO> result = new ArrayList<>();

				try {

					Iterator<Utente> iterator = this.userRepository.findAll().iterator();

					while (iterator.hasNext()) {

						Utente user = iterator.next();
						result.add(UtenteDTO.build(user));

					}

					response.setResult(result);
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//find user by id
			public Response<UtenteDTO> findUtenteById(int id) {

				Response<UtenteDTO> response = new Response<UtenteDTO>();

				try {

					Utente user = this.userRepository.findById(id).get();

					response.setResult(UtenteDTO.build(user));
					response.setResultTest(true);

				} catch (Exception e) {

					response.setError(error);

				}

				return response;

			}

			//update user
			public Response<UtenteDTO> updateUtente(int id, String nome, String cognome, String dataNascita, String email, String password) {

				Response<UtenteDTO> response = new Response<UtenteDTO>();
				try {
					Utente user = this.userRepository.findById(id).get();

					if (nome != null)
						user.setNome(nome);
					
					if (cognome != null)
						user.setCognome(cognome);
					
					if (dataNascita != null)
						user.setDataNascita(dataNascita);
					
					if (email != null)
						user.setEmail(email);
					
					if (password != null)
						user.setPassword(password);
					
					this.userRepository.save(user);
					
					response.setResult(UtenteDTO.build(user));
					response.setResultTest(true);

				} catch (Exception e) {
					
					response.setError(error);
					
				}	

				return response;
			}
}
