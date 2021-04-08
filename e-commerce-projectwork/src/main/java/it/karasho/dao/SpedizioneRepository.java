package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Spedizione;

@Repository
public interface SpedizioneRepository extends CrudRepository<Spedizione, Integer> {

}
