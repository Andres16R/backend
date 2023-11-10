package com.famisanar.arquitectura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.famisanar.arquitectura.shared.config.ModelMapperConfig;

@SpringBootApplication
@Import(ModelMapperConfig.class)
public class ArquitecturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquitecturaApplication.class, args);
	}

}
