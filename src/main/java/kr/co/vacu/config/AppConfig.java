package kr.co.vacu.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

	@Bean public String whiteship() {
		return "Keesun";
	}
	@Bean public String toby() {
		return "toby";
	}
}
