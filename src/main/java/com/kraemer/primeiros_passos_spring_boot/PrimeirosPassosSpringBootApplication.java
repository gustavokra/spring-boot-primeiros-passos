package com.kraemer.primeiros_passos_spring_boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;

@SpringBootApplication
public class PrimeirosPassosSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeirosPassosSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(SistemaMensagem sistemaMensagem) throws Exception {
		return args -> {
			sistemaMensagem.enviarConfirmacaoCadastro();
			sistemaMensagem.enviarMensagemBoasVindas();
			sistemaMensagem.enviarConfirmacaoCadastro();
			sistemaMensagem.enviarMensagemBoasVindas();
		};
	}

}
