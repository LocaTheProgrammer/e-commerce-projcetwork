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
import it.karasho.entity.Articolo;
import it.karasho.service.ArticoloService;


@RestController
@RequestMapping("/rest/articolo")
public class ArticoloRestController {
	
	private static Logger log = LoggerFactory.getLogger(ArticoloRestController.class);
	@Autowired
	ArticoloService articoloService;
	
	
	@PostMapping(path="/create")
	public Response<?> createArticolo(@RequestBody Articolo a){
		log.info("Ricevuta richiesta della creazion prodotto");
		return articoloService.createArticolo(a);
	}
	
	
	@PostMapping(path="/delete")
	public Response<?> deleteArticolo(@RequestBody int id){
		log.info("Ricevuta richiesta della delete prodotto");
		return articoloService.deleteArticoloById(id);
	}
	
	@PostMapping(path="/findById")
	public Response<?> findArticoloById(@RequestBody int id){
		log.info("Ricevuta richiesta della delete prodotto");
		return articoloService.findArticoloById(id);
	}
	
	@GetMapping(path = "/findHot")
	public Response<?> findAllHot() {
		log.info("Ricevuta richiesta della lista di tutti gli articoli hot");
		return articoloService.findAllHot();	
	}
	
	@PostMapping(path="/update")
	public Response<?> updateArticolo(@RequestBody Articolo a){
		log.info("Ricevuta richiesta della update prodotto");
		return articoloService.updateArticolo(a.getId(), a.getNome(), a.getDescrizione(), a.getPrezzo(), a.getFoto());
	}
	
	@GetMapping(path = "/findAll")
	public Response<?> findAllArticoli() {
		log.info("Ricevuta richiesta della lista di tutti gli articoli");
		return articoloService.findAllArticolos();	
	}
	
	

}
