package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DesafioMeuTimeApplicationTest {
    private DesafioMeuTimeApplication application;

    @Before
    public void before(){
        application = new DesafioMeuTimeApplication();
        application.incluirTime(1L,"Internacional", LocalDate.now(),"Vermelho", "Azul");
        application.incluirJogador(1L,1L, "João", LocalDate.now(), 10, BigDecimal.ZERO);
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveLancarExcecaoQuandoIdTimeForRepetido(){
        application.incluirTime(1L,"time", LocalDate.now(),"branco", "azul");
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarErroQuandoTimeNaoExistir(){
        application.incluirJogador(2L,2L, "João", LocalDate.now(), 10, BigDecimal.ZERO);
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveLancarErroQuandoIdJogadorForRepetido(){
        application.incluirJogador(1L,1L, "João", LocalDate.now(), 10, BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarErroQuandoHabilidadeMaior100(){
        application.incluirJogador(2L,1L, "João", LocalDate.now(), 101, BigDecimal.ZERO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarErroQuandoHabilidadeMenor0(){
        application.incluirJogador(2L,1L, "João", LocalDate.now(), -1, BigDecimal.ZERO);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveLancarErroQuandoNaoExisteJogador(){
        application.definirCapitao(2L);
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarErroQuandoBuscaCapitaoENaoExisteTime(){
        application.definirCapitao(1L);
        application.buscarCapitaoDoTime(2L);
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void deveLancarErroQuandoNaoEncontrarCapitao(){
        application.buscarCapitaoDoTime(1L);
    }

    @Test
    public void deveRetornarCapitaoId(){
        application.definirCapitao(1L);
        assertEquals(Long.valueOf(1), application.buscarCapitaoDoTime(1L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveLancarErroQuandoNaoEncontrarJogadorAoBuscarNomeJogador(){
        application.buscarNomeJogador(16L);
    }

    @Test
    public void deveRetornarNomeJogador(){
        assertEquals("João", application.buscarNomeJogador(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarErroQuandoNaoEncontrarTimeAoBuscarNomeTime(){
        application.buscarNomeTime(2L);
    }

    @Test
    public void deveRetornarNomeDoTime(){
        assertEquals("Internacional", application.buscarNomeTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarErroQuandoBuscarJogadoresENaoExistirTime(){
        application.buscarJogadoresDoTime(2L);
    }

    @Test
    public void deveRetornarListaOrdenadaDeJogadoresPorTime(){
        gerarMassaDeDados();
        assertEquals(new ArrayList<Long>(Arrays.asList(1L, 3L, 7L, 13L)), application.buscarJogadoresDoTime(1L));
    }

    private void gerarMassaDeDados() {
        application.incluirTime(2L,"Flamengo", LocalDate.of(2000,05,03),"Vermelho", "Branco");
        application.incluirTime(10L,"Cruzeiro", LocalDate.now(),"Azul", "Branco");
        application.incluirTime(15L,"Palmeiras", LocalDate.now(),"Verde", "Branco");
        application.incluirJogador(7L,1L, "Paulo", LocalDate.of(1999,11,15), 98, BigDecimal.ZERO);
        application.incluirJogador(4L,2L, "Fred", LocalDate.of(1990,01,30), 65, BigDecimal.ONE);
        application.incluirJogador(3L,1L, "Manoel", LocalDate.of(1989,03,1), 68, BigDecimal.TEN);
        application.incluirJogador(13L,1L, "Cristiano", LocalDate.of(1993,10,1), 65, BigDecimal.valueOf(50.6));
    }

    @Test
    public void deveRetornarMelhorJogador(){
        gerarMassaDeDados();
        assertEquals(Long.valueOf(7), application.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    public void deveRetornarJogadorMaisVelho(){
        gerarMassaDeDados();
        assertEquals(Long.valueOf(3), application.buscarJogadorMaisVelho(1L));
    }

    @Test
    public void deveRetornarListaVaziaDeTimes(){
        application.times.clear();
        assertTrue(application.buscarTimes().isEmpty());
    }

    @Test
    public void deveRetornarListaOrdenadaTimes(){
        gerarMassaDeDados();
        assertEquals(new ArrayList<Long>(Arrays.asList(1L,2L,10L,15L)), application.buscarTimes());
    }

    @Test
    public void deveRetornarJogadorMaiorSalario(){
        gerarMassaDeDados();
        assertEquals(Long.valueOf(13L), application.buscarJogadorMaiorSalario(1L));
    }

    @Test
    public void deveRetornarSalarioJogador(){
        gerarMassaDeDados();
        assertEquals(BigDecimal.valueOf(50.6), application.buscarSalarioDoJogador(13L));
    }

    @Test
    public void deveRetornarListaTopJogadores(){
        gerarMassaDeDados();
        assertEquals(new ArrayList<>(Arrays.asList(7L,3L,4L)), application.buscarTopJogadores(3));
    }

    @Test
    public void deveRetornarArrayVazio(){
        application.jogadores.clear();
        assertEquals(new ArrayList<Long>(), application.buscarTopJogadores(3));
    }

    @Test
    public void deveRetornarCamisaSecundariaTimeFora(){
        gerarMassaDeDados();
        assertEquals("Branco", application.buscarCorCamisaTimeDeFora(1L,2L));
    }

    @Test
    public void deveRetornarCamisaPrincipalTimeFora(){
        gerarMassaDeDados();
        assertEquals("Azul", application.buscarCorCamisaTimeDeFora(1L,10L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveLancarExcecaoSeUmDosTimesNaoExistir(){
        application.buscarCorCamisaTimeDeFora(1L,2L);
    }


}