package com.challenge.testes;


import com.challenge.desafio.CalculadorDeClasses;
import com.challenge.interfaces.Calculavel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CalculadorDeClassesTest {
    private static Calculavel calculadora;

    @BeforeClass
    public static void setUp(){
        calculadora = new CalculadorDeClasses();
    }

    @Test
    public void deveSomarSomenteValoresAnotadosComSomarDoTipoBigDecimal() throws InstantiationException, IllegalAccessException {
        assertEquals(BigDecimal.TEN,calculadora.somar(new ClasseTeste()));
    }

    @Test
    public void deveSomarSomenteValoresAnotadosComSubtrairDoTipoBigDecimal() throws InstantiationException, IllegalAccessException {
        assertEquals(BigDecimal.TEN,calculadora.subtrair(new ClasseTeste()));
    }

    @Test
    public void deveSubtrairValoresAnotadosComSubtrairDosAnotadosComSomar() throws InstantiationException, IllegalAccessException {
        assertEquals(BigDecimal.ZERO,calculadora.totalizar(new ClasseTeste()));
    }

    @Test
    public void deveRetornarZeroQuandoNaoTiverAnotacoesAoTotalizar() throws InstantiationException, IllegalAccessException {
        assertEquals(BigDecimal.ZERO,calculadora.totalizar(ClasseTesteSemAnotacoes.class));
    }

    @Test
    public void deveRetornarZeroQuandoNaoTiverAnotacoesAoSomar() throws InstantiationException, IllegalAccessException {
        assertEquals(BigDecimal.ZERO,calculadora.somar(ClasseTesteSemAnotacoes.class));
    }

    @Test
    public void deveRetornarZeroQuandoNaoTiverAnotacoesAoSubtrair() throws InstantiationException, IllegalAccessException {
        assertEquals(BigDecimal.ZERO,calculadora.subtrair(ClasseTesteSemAnotacoes.class));
    }
}
