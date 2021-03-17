package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Carrello;

@Repository
public interface CarrelloRepository extends CrudRepository<Carrello, Integer> {

}
