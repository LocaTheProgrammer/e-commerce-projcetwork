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
import it.karasho.entity.Carrello;
import it.karasho.service.CarrelloService;

@RestController
@RequestMapping("/rest/carrello")
public class CarrelloRestController {
	
	private static Logger log = LoggerFactory.getLogger(CarrelloRestController.class);
	@Autowired
	CarrelloService carrelloService;
	
	
	@PostMapping(path="/create")
	public Response<?> createCarrello(@RequestBody Carrello c){
		log.info("Ricevuta richiesta della creazion carrello");
		return carrelloService.createCarrello(c);
	}
	
	
	@PostMapping(path="/delete")
	public Response<?> deleteCarrello(@RequestBody int id){
		log.info("Ricevuta richiesta della delete carrello");
		return carrelloService.deleteCarrelloById(id);
	}
	
	@PostMapping(path="/findById")
	public Response<?> findCarrelloById(@RequestBody int id){
		log.info("Ricevuta richiesta della delete carrello");
		return carrelloService.findCarrelloById(id);
	}
	
	@PostMapping(path="/update")
	public Response<?> updateCarrello(@RequestBody Carrello c){
		log.info("Ricevuta richiesta della update carrello");
		return carrelloService.updateCarrello(c.getId(), c.getIdArticolo(), c.getQuantita(), c.getIdUtente());
	}
	
	@GetMapping(path = "/findAll")
	public Response<?> findAllArticoli() {
		log.info("Ricevuta richiesta della lista di tutti gli articoli");
		return carrelloService.findAllCarrellos();	
	}
	

}
