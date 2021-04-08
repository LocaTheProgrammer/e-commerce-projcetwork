package it.karasho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "spedizione")
@Data
public class Spedizione {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="email_utente")
	private String emailUtente;
	
	@Column(name="totale")
	private double totale;
	
	@Column(name="idArticoli")
	private String idArticoli;
	
	@Column(name="quantita")
	private String quantita;
	
	@Column(name="indirizzo")
	private String indirizzo;
	
	@Column(name="cap")
	private String cap;
	
	@Column(name="nazione")
	private String nazione;
	
	@Column(name="citta")
	private String citta;
	
	
	
	
}
