package com.suman.springsecurity;

import com.suman.springsecurity.jpt.service.GetEndpoints;
import com.suman.springsecurity.jpt.service.KeycloakConfigJsonGeneration;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {

	private final GetEndpoints getEndpoints;
	private final KeycloakConfigJsonGeneration keycloakConfigJsonGeneration;

	public SpringsecurityApplication(GetEndpoints getEndpoints, KeycloakConfigJsonGeneration keycloakConfigJsonGeneration) {
		this.getEndpoints = getEndpoints;
		this.keycloakConfigJsonGeneration = keycloakConfigJsonGeneration;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.registerModule(new RecordModule());
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		getEndpoints.getAllApiEndpointsByController();
		keycloakConfigJsonGeneration.generateConfigurationAsJsonFile();
	}
}
