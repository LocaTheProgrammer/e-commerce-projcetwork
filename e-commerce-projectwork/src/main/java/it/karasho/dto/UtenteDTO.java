package it.karasho.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtenteDTO {
	
	private int id;

	private String nome;

	private String conome;
	
	private String dataNascita;
	
	private String email;
	
	private String password;

}
