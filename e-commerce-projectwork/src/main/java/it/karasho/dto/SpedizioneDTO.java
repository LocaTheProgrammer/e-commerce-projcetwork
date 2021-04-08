package it.karasho.dto;


import org.springframework.beans.BeanUtils;

import it.karasho.entity.Spedizione;
import lombok.Data;

@Data
public class SpedizioneDTO {
	
	
	private int id;
	
	private String emailUtente;
	
	private double totale;
	
	private String idArticoli;
	
	private String quantita;
	
	private String indirizzo;

	private String cap;

	private String nazione;
	
	private String citta;
	
	
	public static SpedizioneDTO build(Spedizione s) {

		SpedizioneDTO result = new SpedizioneDTO();
		BeanUtils.copyProperties(s, result);

		return result;
	}


}
