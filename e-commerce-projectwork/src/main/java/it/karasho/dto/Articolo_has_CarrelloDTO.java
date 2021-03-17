package it.karasho.dto;


import org.springframework.beans.BeanUtils;

import it.karasho.entity.Articolo_has_Carrello;
import lombok.Data;

@Data
public class Articolo_has_CarrelloDTO {
	
	private int id;
	
	private int idUtente;
	
	private int idArticolo;
	
	private int quantitaArticoli;
	
	public static Articolo_has_CarrelloDTO build(Articolo_has_Carrello ahi) {

		Articolo_has_CarrelloDTO result = new Articolo_has_CarrelloDTO();
		BeanUtils.copyProperties(ahi, result);

		return result;
	}
}
