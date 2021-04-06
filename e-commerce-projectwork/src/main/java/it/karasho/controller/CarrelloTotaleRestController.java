package it.karasho.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.karasho.dto.Response;
import it.karasho.service.CarrelloTotaleService;

@RestController
@RequestMapping("/rest/carrelloTotale")
public class CarrelloTotaleRestController {
	private static Logger log = LoggerFactory.getLogger(CarrelloTotaleRestController.class);
	@Autowired
	private CarrelloTotaleService carrelloTotaleService;
	
	@PostMapping(path = "/findTotale")
	public Response<?> findAllArticoli(@RequestBody String body) {
		log.info("Ricevuta richiesta del totale del carrello");
		log.info("body: "+body);
		return carrelloTotaleService.findTotaleByEmail(body);
	}
	
}
