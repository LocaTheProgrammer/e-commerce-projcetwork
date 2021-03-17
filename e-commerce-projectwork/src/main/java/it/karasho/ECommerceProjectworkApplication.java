package it.karasho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = "it.gestionalejaclsg.jac.entity")
public class ECommerceProjectworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceProjectworkApplication.class, args);
	}

}
