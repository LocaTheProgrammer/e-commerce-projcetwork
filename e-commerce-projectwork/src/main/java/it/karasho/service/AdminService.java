package it.karasho.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.karasho.dao.AdminRepository;
import it.karasho.dto.AdminDTO;
import it.karasho.dto.Response;
import it.karasho.entity.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	
	private static Logger log = LoggerFactory.getLogger(AdminService.class);

	public Response<Boolean> createAdmin(Admin admin) {

		Response<Boolean> response=new Response<Boolean>();

		try {

			this.adminRepository.save(admin);

			response.setResult(true);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Admin non creato");

		}

		return response;

	}

	public Response<String> deleteAdminById(int id) {

		Response<String> response = new Response<String>();

		try {

			this.adminRepository.deleteById(id);

			response.setResult("Admin eliminato.");
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Admin non eliminato correttamente.");

		}

		return response;

	}

	public Response<List<AdminDTO>> findAllAdmins() {

		Response<List<AdminDTO>> response = new Response<List<AdminDTO>>();

		List<AdminDTO> result = new ArrayList<>();

		try {

			Iterator<Admin> iterator = this.adminRepository.findAll().iterator();

			while (iterator.hasNext()) {

				Admin admin = iterator.next();
				result.add(AdminDTO.build(admin));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

	public Response<AdminDTO> findAdminById(int id) {

		Response<AdminDTO> response = new Response<AdminDTO>();

		try {

			Admin admin = this.adminRepository.findById(id).get();

			response.setResult(AdminDTO.build(admin));
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}


	public Response<AdminDTO> findAdminByAdminnameAndPassword(String adminname, String password) {

		Response<AdminDTO> response = new Response<AdminDTO>();
		
//		log.info("admin name: "+adminname);
//		log.info("admin pwd: "+password);
		try {

			Admin admin = this.adminRepository.findByUsername(adminname);
			
			log.info("admin name:" +admin.getUsername());

			if(admin.getPassword().equals(password)) {
				
				response.setResult(AdminDTO.build(admin));
				response.setResultTest(true);
				log.info("\n\nUTENTE TROVATO!\n\n");

			}else {
				log.info("\n\nUTENTE NON TROVATO!\n\n");
			}


		} catch (Exception e ) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}
	
	public Response<Admin> findAdminByAdminname(String adminname) {

		Response<Admin> response = new Response<Admin>();

		try {

			Admin admin = this.adminRepository.findByUsername(adminname);
			response.setResult(admin);

		} catch (Exception e ) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}


	public Response<AdminDTO> updateAdminAdminnameById(int id, String newAdminname) {

		Response<AdminDTO> response = new Response<AdminDTO>();

		try {

			Admin admin = this.adminRepository.findById(id).get();

			if (admin != null)
				admin.setUsername(newAdminname);

			this.adminRepository.save(admin);

			response.setResult(AdminDTO.build(admin));
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
		}	

		return response;

	}

	public Response<AdminDTO> updateAdminPasswordById(int id, String newPassword) {

		Response<AdminDTO> response = new Response<AdminDTO>();

		try {

			Admin admin = this.adminRepository.findById(id).get();

			if (admin != null)
				admin.setPassword(newPassword);

			this.adminRepository.save(admin);

			response.setResult(AdminDTO.build(admin));
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}	

		return response;

	}

}