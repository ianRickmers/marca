package edu.migsw.marca;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.migsw.marca.services.MarcaService;

@SpringBootApplication
public class MarcaApplication implements CommandLineRunner {

	@Resource
	private MarcaService marcaService;

	public static void main(String[] args) {
		SpringApplication.run(MarcaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		marcaService.deleteDirectory();
		marcaService.init();
	}
}
