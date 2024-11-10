package com.kraemer.primeiros_passos_spring_boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrimeirosPassosSpringBootApplicationTests {

	@Test
	void contextLoads() {
		Calculadora calculadora = new Calculadora();
		int resultado = calculadora.somar(1, 3);
		Assertions.assertEquals(4, resultado);
	}

}
