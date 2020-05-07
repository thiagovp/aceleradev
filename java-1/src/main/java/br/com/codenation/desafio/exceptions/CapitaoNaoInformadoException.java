package br.com.codenation.desafio.exceptions;

public class CapitaoNaoInformadoException extends RuntimeException {

    public CapitaoNaoInformadoException() {
        super("Capitão não foi informado.");
    }
}

