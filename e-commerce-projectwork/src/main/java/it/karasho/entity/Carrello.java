package it.karasho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "carrello")
@Data
public class Carrello {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="id_articolo")
	private int idArticolo;
	
	@Column(name="quantita")
	private int quantita;
	
	@Column(name="taglia")
	private String taglia;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@Column(name="prezzo")
	private double prezzo;
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="email_utente")
	private String emailUtente;
	
	@Column(name="totale")
	private double totale;
	

}
