package kr.ferreiras.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * Projeto Spring Boot gerado via Spring Initializr.
 * Módulos:
 * - Spring Web
 * - Spring Data JPA
 * - H2 Database
 * - OpenFeign
 *
 * @author krferreiras
 */

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
