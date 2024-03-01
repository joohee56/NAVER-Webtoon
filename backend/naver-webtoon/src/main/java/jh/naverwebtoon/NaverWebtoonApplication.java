package jh.naverwebtoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NaverWebtoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaverWebtoonApplication.class, args);
	}

}
