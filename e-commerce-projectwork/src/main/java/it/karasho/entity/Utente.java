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
public class Utente {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="nome")
	private String nome;

	@Column(name="conome")
	private String conome;
	
	@Column(name="dataNascita")
	private String dataNascita;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	

}
