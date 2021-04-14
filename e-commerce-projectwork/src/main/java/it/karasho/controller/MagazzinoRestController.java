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
import it.karasho.entity.Magazzino;
import it.karasho.service.MagazzinoService;

@RestController
@RequestMapping("/rest/magazzino")
public class MagazzinoRestController {
	
	private static Logger log = LoggerFactory.getLogger(MagazzinoRestController.class);
	@Autowired
	MagazzinoService magazzinoService;
	
	
	@PostMapping(path="/create")
	public Response<?> createMagazzino(@RequestBody Magazzino m){
		log.info("Ricevuta richiesta della creazion magazzino");
		return magazzinoService.createMagazzino(m);
	}
	
	
	@PostMapping(path="/delete")
	public Response<?> deleteMagazzino(@RequestBody int id){
		log.info("Ricevuta richiesta della delete magazzino");
		return magazzinoService.deleteMagazzinoById(id);
	}
	
	@PostMapping(path="/findById")
	public Response<?> findMagazzinoById(@RequestBody int id){
		log.info("Ricevuta richiesta della delete magazzino");
		return magazzinoService.findMagazzinoById(id);
	}
	
	@PostMapping(path="/update")
	public Response<?> updateMagazzino(@RequestBody Magazzino m){
		log.info("Ricevuta richiesta della update magazzino");
		return magazzinoService.updateMagazzino(m.getId(), m.getIdArticolo(), m.getDisponibilita(), m.getPreorder());
	}
	
	@GetMapping(path = "/findAll")
	public Response<?> findAllArticoli() {
		log.info("Ricevuta richiesta della lista di MAGAZZINO");
		return magazzinoService.findAllMagazzinos();	
	}
	
	
}
