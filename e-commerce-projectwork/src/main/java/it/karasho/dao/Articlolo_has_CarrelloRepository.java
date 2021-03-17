package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Articolo_has_Carrello;

@Repository
public interface Articlolo_has_CarrelloRepository extends CrudRepository<Articolo_has_Carrello, Integer> {

}
