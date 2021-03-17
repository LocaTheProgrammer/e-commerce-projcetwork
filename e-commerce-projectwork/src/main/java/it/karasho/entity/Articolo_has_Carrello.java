package it.karasho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "utente")
@Data
public class Articolo_has_Carrello {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_utente")
	private int idUtente;
	
	@Column(name = "id_articolo")
	private int idArticolo;
	
	@Column(name = "quantita_articoli")
	private int quantitaArticoli;
	
	

}
