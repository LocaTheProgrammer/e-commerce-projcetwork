package it.karasho.dto;

import org.springframework.beans.BeanUtils;

import it.karasho.entity.Utente;
import lombok.Data;

@Data
public class UtenteDTO {
	
	private int id;

	private String nome;

	private String cognome;
	
	private String dataNascita;
	
	private String email;
	
	private String password;
	
	public static UtenteDTO build(Utente u) {

		UtenteDTO result = new UtenteDTO();
		BeanUtils.copyProperties(u, result);

		return result;
	}

}
