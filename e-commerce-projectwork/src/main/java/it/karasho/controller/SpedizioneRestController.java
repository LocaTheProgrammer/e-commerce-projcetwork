package it.karasho.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.karasho.dto.CarrelloDTO;
import it.karasho.dto.CarrelloTotaleDTO;
import it.karasho.dto.Response;

import it.karasho.entity.Spedizione;
import it.karasho.service.CarrelloService;
import it.karasho.service.CarrelloTotaleService;
import it.karasho.service.SpedizioneService;

@RestController
@RequestMapping("/rest/spedizione")
public class SpedizioneRestController {
	
	private static Logger log = LoggerFactory.getLogger(SpedizioneRestController.class);
	@Autowired
	SpedizioneService spedizioneService;
	
	@Autowired
	CarrelloService carrelloService;
	
	@Autowired
	CarrelloTotaleService carrelloTotaleService;
	
	@PostMapping(path="/create")
	public Response<?> createSpedizione(@RequestBody Spedizione s){
		log.info("Ricevuta richiesta della spedizione");
	
		
		String idArticoli="";
		String quantita="";
		Response<CarrelloTotaleDTO> cTot=carrelloTotaleService.findTotaleByEmail(s.getEmailUtente());
		double totale=cTot.getResult().getTotale();
		Response<List<CarrelloDTO>> c=carrelloService.findJustAllCarrellosByEmail(s.getEmailUtente());
		
		for(int i=0;i<c.getResult().size();i++) {
			idArticoli+=c.getResult().get(i).getIdArticolo()+";";
			quantita+=c.getResult().get(i).getQuantita()+";";
		}
		
		
		s.setIdArticoli(idArticoli);
		s.setQuantita(quantita);
		s.setTotale(totale);
		log.info("spedizione: "+s);
		return spedizioneService.createSpedizione(s);
	}

	
	@PostMapping(path="/delete")
	public Response<?> deleteSpedizione(@RequestBody int id){
		log.info("Ricevuta richiesta della delete prodotto");
		return spedizioneService.deleteSpedizioneById(id);
	}
	
	@PostMapping(path="/findById")
	public Response<?> findSpedizioneById(@RequestBody int id){
		log.info("Ricevuta richiesta della delete prodotto");
		return spedizioneService.findSpedizioneById(id);
	}
	
	@GetMapping(path = "/findAll")
	public Response<?> findAllArticoli() {
		log.info("Ricevuta richiesta della lista di tutti gli articoli");
		return spedizioneService.findAllSpediziones();	
	}
}
