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
import it.karasho.service.ArticoloService;
import it.karasho.service.CarrelloService;

@RestController
@RequestMapping("/rest/carrello")
public class CarrelloRestController {
	
	private static Logger log = LoggerFactory.getLogger(CarrelloRestController.class);
	@Autowired
	CarrelloService carrelloService;
	@Autowired
	ArticoloService articoloService;
	
	@PostMapping(path="/create")
	public Response<?> createCarrello(@RequestBody Carrello c){
		log.info("Ricevuta richiesta della creazion carrello");
		
		log.info("carrello ricevuto: "+c);
		

		c.setPrezzo(articoloService.findArticoloById(c.getIdArticolo()).getResult().getPrezzo());
		c.setNome(articoloService.findArticoloById(c.getIdArticolo()).getResult().getNome());		
		c.setFoto(articoloService.findArticoloById(c.getIdArticolo()).getResult().getFoto());
		c.setDescrizione(articoloService.findArticoloById(c.getIdArticolo()).getResult().getDescrizione());
		c.setTotale(c.getPrezzo()*c.getQuantita());
		return carrelloService.createCarrello(c);
	}
	
	
	@PostMapping(path="/delete")
	public Response<?> deleteCarrello(@RequestBody String body){
		log.info("body: "+body);
		int conta = 0;
		int[] arr = new int[body.length()];
		for (int i = 0; i < body.length(); i++) {
			if (body.charAt(i) == '"') {
				arr[conta] = i;
				conta++;
			}
		}
		log.info("\n\n inizio substrings \n\n");

		String id = body.substring(arr[2] + 1, arr[3]);
		String email = body.substring(arr[6] + 1, arr[7]);
		
		log.info("id: "+id);
		log.info("email: "+email);
		int idInt=Integer.parseInt(id);
	return carrelloService.deleteCarrelloById(idInt, email);
	}
//	@PostMapping(path="/delete")
//	public void deleteCarrello(@RequestBody String body){
//		log.info("Ricevuta richiesta della delete carrello"+ body);
//		log.info("\n\n\n\nbody: " + body + "\n\n\n"+"eliminazione\n\n\n");
//		int pPartenza=0;
//		int pArrivo=0;
//		
//		for (int i = 0; i < body.length(); i++) {
//			if (body.charAt(i) == ':') {
//				pPartenza = i+1;
//			}
//			if(body.charAt(i)=='}') {
//				pArrivo=i;
//			}
//		}
//		String id = body.substring(pPartenza,pArrivo);
//		log.info("id: --> "+ id);
//		int idInt=Integer.parseInt(id);
//		return carrelloService.deleteCarrelloById(idInt);
//	}
	
	@PostMapping(path="/findById")
	public Response<?> findCarrelloById(@RequestBody int id){
		log.info("Ricevuta richiesta della delete carrello");
		return carrelloService.findCarrelloById(id);
	}
	
	@PostMapping(path="/update")
	public Response<?> updateCarrello(@RequestBody Carrello c){
		log.info("Ricevuta richiesta della update carrello");
		return carrelloService.updateCarrello(c.getId(), c.getIdArticolo(), c.getQuantita(), c.getEmailUtente());
	}
	
	@GetMapping(path = "/findAll")
	public Response<?> findAllArticoli() {
		log.info("Ricevuta richiesta della lista di tutti i carrelli");
		return carrelloService.findAllCarrellos();	
	}
	

}
