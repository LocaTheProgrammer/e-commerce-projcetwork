package it.karasho.dto;

import org.springframework.beans.BeanUtils;

import it.karasho.entity.Carrello;
import lombok.Data;

@Data
public class CarrelloDTO {
	
	private int id;
	
	private int idArticolo;
	
	private int quantita;
	
	private String emailUtente;
	
	private String taglia;
	
	private String nome;

	private String descrizione;
	
	private double prezzo;
	private double totale;
	private String foto;
	
	public static CarrelloDTO build(Carrello c) {

		CarrelloDTO result = new CarrelloDTO();
		BeanUtils.copyProperties(c, result);

		return result;
	}

}
