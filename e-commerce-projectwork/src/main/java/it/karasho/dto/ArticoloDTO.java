package it.karasho.dto;

import org.springframework.beans.BeanUtils;

import it.karasho.entity.Articolo;

import lombok.Data;

@Data
public class ArticoloDTO {
	
	private int id;
	
	private String nome;

	private String descrizione;
	
	private double prezzo;

	public static ArticoloDTO build(Articolo a) {

		ArticoloDTO result = new ArticoloDTO();
		BeanUtils.copyProperties(a, result);

		return result;
	}
	
}
