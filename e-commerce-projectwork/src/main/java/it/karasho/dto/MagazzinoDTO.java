package it.karasho.dto;

import org.springframework.beans.BeanUtils;

import it.karasho.entity.Magazzino;
import lombok.Data;

@Data
public class MagazzinoDTO {

	private int id;
	
	private int idArticolo;
	
	private int disponibilita;
	
	private int preorder;
	
	public static MagazzinoDTO build(Magazzino m) {

		MagazzinoDTO result = new MagazzinoDTO();
		BeanUtils.copyProperties(m, result);

		return result;
	}
	
}
