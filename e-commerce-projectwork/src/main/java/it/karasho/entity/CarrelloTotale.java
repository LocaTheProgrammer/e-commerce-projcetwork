package it.karasho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "carrello_totale")
@Data
public class CarrelloTotale {
	

	
	@Column(name="totale")
	private double totale;
	
	@Id
	@Column(name="email_utente")
	private String emailUtente;
	
	

}