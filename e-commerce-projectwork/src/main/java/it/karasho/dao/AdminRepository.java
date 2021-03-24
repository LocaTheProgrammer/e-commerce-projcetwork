package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	public Admin findByUsername(String username);
	
	
}