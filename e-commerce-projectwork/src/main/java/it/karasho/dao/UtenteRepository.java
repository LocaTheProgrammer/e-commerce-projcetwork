package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer> {

}
