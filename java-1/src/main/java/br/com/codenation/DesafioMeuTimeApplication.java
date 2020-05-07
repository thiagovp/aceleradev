package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.entidades.Jogador;
import br.com.codenation.desafio.entidades.Model;
import br.com.codenation.desafio.entidades.Time;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    List<Time> times = new ArrayList<>();
    List<Jogador> jogadores = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException {
        Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        validarExisteIdentificador(times, time);
        times.add(time);
    }


    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws TimeNaoEncontradoException, IdentificadorUtilizadoException {
        validarNivelHabilidade(nivelHabilidade);
        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        validarExisteIdentificador(jogadores, jogador);
        validarExisteTimeParaJogador(jogador.getIdTime());
        jogadores.add(jogador);
    }

    private void validarNivelHabilidade(Integer nivelHabilidade) throws IllegalArgumentException {
        if(nivelHabilidade> 100 || nivelHabilidade < 0){
            throw new IllegalArgumentException();
        }
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException {
		Jogador jogador = buscarJogadorPorId(idJogador);
		jogadores.stream()
				.filter(capitao-> capitao.getIdTime().equals(jogador.getIdTime()) && capitao.isCapitao())
                .findAny()
				.ifPresent(capitao -> capitao.setCapitao(false));
		jogadores.stream()
				.filter(novoCapitao -> novoCapitao.getId().equals(jogador.getId()))
                .findAny()
				.ifPresent(novoCapitao -> novoCapitao.setCapitao(true));


	}

	private Jogador buscarJogadorPorId(Long idJogador) {
		return jogadores.stream()
				.filter(jogador -> jogador.getId().equals(idJogador))
                .findAny()
				.orElseThrow(JogadorNaoEncontradoException::new);
	}

    private Time buscarTimePorId(Long idTime) {
        times.size();
        return times.stream()
                .filter(time -> time.getId().equals(idTime))
                .findAny()
                .orElseThrow(TimeNaoEncontradoException::new);
    }

	@Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException {
        validarExisteTimeParaJogador(idTime);
        return validarExisteCapitaoParaTime(idTime).getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        return buscarJogadorPorId(idJogador).getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        return buscarTimePorId(idTime).getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        validarExisteTimeParaJogador(idTime);
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .map(Model::getId)
                .sorted()
                .collect(Collectors.toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        validarExisteTimeParaJogador(idTime);
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        validarExisteTimeParaJogador(idTime);
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .min(Comparator.comparing(Jogador::getDataNascimento)).get().getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        return times.stream().map(Model::getId).sorted().collect(Collectors.toList());
    }


    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        validarExisteTimeParaJogador(idTime);
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime))
                .max(Comparator.comparing(Jogador::getSalario)).get().getId();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return buscarJogadorPorId(idJogador).getSalario();
    }

    public static void validarExisteIdentificador(List<? extends Model> lista, Model model) throws IdentificadorUtilizadoException {
        if(lista.contains(model)){
            throw new IdentificadorUtilizadoException();
        }
    }

    public void validarExisteTimeParaJogador(Long idTime) throws TimeNaoEncontradoException {
        if(!times.contains(new Time(idTime))){
            throw new TimeNaoEncontradoException();
        }
    }

    public Jogador validarExisteCapitaoParaTime(Long idTime) throws CapitaoNaoInformadoException{
        return jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime) && jogador.isCapitao())
                .findAny()
                .orElseThrow(CapitaoNaoInformadoException::new);
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        return jogadores.stream()
                .sorted(Comparator.comparingInt(Jogador::getNivelHabilidade)
                        .reversed()
                        .thenComparing(Model::getId))
                .limit(top)
                .map(Model::getId)
                .collect(Collectors.toList());
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        final Time timeCasa = buscarTimePorId(timeDaCasa);
        final Time timeFora = buscarTimePorId(timeDeFora);

        if(timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())){
            return timeFora.getCorUniformeSecundario();
        }
        return timeFora.getCorUniformePrincipal();
    }

}
