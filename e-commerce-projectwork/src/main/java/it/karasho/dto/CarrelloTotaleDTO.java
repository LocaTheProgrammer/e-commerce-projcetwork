package it.karasho.dto;

import org.springframework.beans.BeanUtils;

import it.karasho.entity.CarrelloTotale;
import lombok.Data;

@Data
public class CarrelloTotaleDTO {
	
	private int id;
	
	private double totale;
	
	private String emailUtente;
	
	public static CarrelloTotaleDTO build(CarrelloTotale ct) {

		CarrelloTotaleDTO result = new CarrelloTotaleDTO();
		BeanUtils.copyProperties(ct, result);

		return result;
	}


}
