package it.karasho.dto;

import org.springframework.beans.BeanUtils;

import it.karasho.entity.Admin;
import lombok.Data;

@Data
public class AdminDTO {

	private int id;

	private String username;

	private String password;

	private String roles;
	
	public static AdminDTO build(Admin admin) {

		AdminDTO result = new AdminDTO();
		BeanUtils.copyProperties(admin, result);

		return result;
	}
	
}