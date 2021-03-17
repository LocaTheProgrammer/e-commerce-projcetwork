package it.karasho.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Articolo_has_CarrelloDTO {
	
	private int id;
	
	private int idUtente;
	
	private int idArticolo;
	
	private int quantitaArticoli;
	
}
