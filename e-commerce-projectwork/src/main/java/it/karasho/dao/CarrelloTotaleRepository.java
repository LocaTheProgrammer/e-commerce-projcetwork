package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.CarrelloTotale;

@Repository
public interface CarrelloTotaleRepository extends CrudRepository<CarrelloTotale, Integer>{
	public CarrelloTotale findByEmailUtente(String email);
}
