package br.ifmg.projeto1_2026;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Project12026App {

	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(Project12026App.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String senha = encoder.encode("12345");
		System.out.println("Nova senha: "+ senha);
	}

	//jogar a senha no import (insert into do sql)
}
