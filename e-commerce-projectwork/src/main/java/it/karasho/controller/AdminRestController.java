package it.karasho.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.karasho.dto.Response;
import it.karasho.entity.Admin;
import it.karasho.service.AdminService;

@RestController
@RequestMapping("/rest/admin")
public class AdminRestController {
	private static Logger log = LoggerFactory.getLogger(AdminRestController.class);


	@Autowired
	private AdminService userService;

	@PostMapping("/create")
	public Response<?> createAdmin(@RequestBody Admin admin){

		log.info("Ricevuta richiesta di creazione nuovo admin");
		
		log.info("nome "+ admin.getUsername());
		log.info("nome "+ admin.getUsername());
		log.info("ruolo "+ admin.getRoles());
		return userService.createAdmin(admin);
	}



	@DeleteMapping(path = "/delete/{id}")
	public Response<?> deleteAdminById(@PathVariable int id) {

		log.info("Richiesta delete.");

		return userService.deleteAdminById(id);
	}


	@GetMapping(path="/findAll")
	public Response<?> findAllAdmins(){

		log.info("richiesta di find all.");

		return userService.findAllAdmins();
	}
	@GetMapping(path="/findAdminsPassword/{}")
	public Response<?> findAllAdminsPassword(){

		log.info("richiesta di find all.");

		return userService.findAllAdmins();
	}


	@GetMapping(path="/findById/{id}")
	public Response<?> findAdminById(@PathVariable int id){
		log.info("trova da id");

		return userService.findAdminById(id);
	}

	@PostMapping(path="/signIn")
	public Response<?> findAdminByAdminnamePassword(
			@RequestBody Admin admin){

		

		return userService.findAdminByAdminnameAndPassword(admin.getUsername(), admin.getPassword());
	}



}