package it.karasho.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticoloDTO {
	
	private int id;
	
	private String nome;

	private String descrzione;
	
	private String prezzo;
	
	
}
