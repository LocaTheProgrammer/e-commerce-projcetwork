package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Magazzino;

@Repository
public interface MagazzinoRepository extends CrudRepository<Magazzino, Integer> {

}
