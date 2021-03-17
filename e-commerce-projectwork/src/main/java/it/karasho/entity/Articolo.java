package it.karasho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "articolo")
@Data
public class Articolo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome_articolo")
	private String nome;
	
	@Column(name="descrizione")
	private String descrzione;
	
	@Column(name="prezzo")
	private String prezzo;
	
	
	

}