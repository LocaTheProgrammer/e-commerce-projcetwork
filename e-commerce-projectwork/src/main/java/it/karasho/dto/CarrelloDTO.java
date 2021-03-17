package it.karasho.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarrelloDTO {
	
	private int id;
	
	private int idArticolo;
	
	private int quantita;
	
	private int idUtente;


}
