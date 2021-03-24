package it.karasho.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.karasho.dto.Response;
import it.karasho.entity.Utente;
import it.karasho.service.UtenteService;

@RestController
@RequestMapping("/rest/utente")
public class UtenteRestController {
	
	private static Logger log = LoggerFactory.getLogger(UtenteRestController.class);
	@Autowired
	UtenteService utenteService;
	
	
	@PostMapping(path="/create")
	public Response<?> createUtente(@RequestBody Utente u){
		log.info("Ricevuta richiesta della creazion utente");
		return utenteService.createUtente(u);
	}
	
	
	@PostMapping(path="/delete")
	public Response<?> deleteUtente(@RequestBody int id){
		log.info("Ricevuta richiesta della delete utente");
		return utenteService.deleteUtenteById(id);
	}
	
	@PostMapping(path="/findById")
	public Response<?> findUtenteById(@RequestBody int id){
		log.info("Ricevuta richiesta della delete utente");
		return utenteService.findUtenteById(id);
	}
	
	@PostMapping(path="/update")
	public Response<?> updateUtente(@RequestBody Utente u){
		log.info("Ricevuta richiesta della update utente");
		return utenteService.updateUtente(u.getId(), u.getNome(), u.getCognome(), u.getDataNascita(), u.getEmail(), u.getPassword());
	}
	
	@GetMapping(path = "/findAll")
	public Response<?> findAllArticoli() {
		log.info("Ricevuta richiesta della lista di tutti gli articoli");
		return utenteService.findAllUtentes();	
	}
	

}
