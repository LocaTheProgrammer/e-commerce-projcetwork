package it.karasho.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MagazzinoDTO {

	private int id;
	
	private int idArticolo;
	
	private int disponibilita;
	
}
