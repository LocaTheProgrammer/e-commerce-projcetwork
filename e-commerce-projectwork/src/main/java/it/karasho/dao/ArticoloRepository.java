package it.karasho.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.karasho.entity.Articolo;


@Repository
public interface ArticoloRepository extends CrudRepository<Articolo, Integer> {

}
