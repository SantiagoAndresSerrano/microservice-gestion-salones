package com.springboot.app.gestionsalones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringbootServicioGestionSalonesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioGestionSalonesApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
				registry.addMapping("/**").allowedOrigins("https://microservices-frontend-ufps.vercel.app").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

}