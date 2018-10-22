package com.bibiloteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bibiloteca.context.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class PortifolioEmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortifolioEmpresaApplication.class, args);
	}
}
