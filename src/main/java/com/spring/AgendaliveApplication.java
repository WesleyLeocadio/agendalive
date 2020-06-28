package com.spring;

import com.spring.domain.LiveDomain;
import com.spring.repository.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootApplication
public class AgendaliveApplication  implements CommandLineRunner {

	@Autowired
	private LiveRepository liveRepository;


	public static void main(String[] args) {
		SpringApplication.run(AgendaliveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LiveDomain liv1 = new LiveDomain(null, "Wesley Leocadio", "WesleyLeocadio",sdf.parse("30-09-2017 10:32"),"https://www.youtube.com/",sdf.parse("30-09-2017 10:32"));
		LiveDomain liv2 = new LiveDomain(null, "Tiringa", "Comédia Selvagem",sdf.parse("30-09-2020 10:32"),"https://www.youtube.com/",sdf.parse("30-09-2020 11:32"));

		liveRepository.saveAll(Arrays.asList(liv1, liv2));
	}
}
