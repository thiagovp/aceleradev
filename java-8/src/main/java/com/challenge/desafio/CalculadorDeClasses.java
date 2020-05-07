package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object objeto) throws IllegalAccessException {
        return calcularValoresPorAnnotation(objeto, Somar.class);
    }

    private BigDecimal calcularValoresPorAnnotation(Object objeto, Class<? extends Annotation> annotation) throws IllegalAccessException {
        BigDecimal valor = BigDecimal.ZERO;
        Field[] campos = objeto.getClass().getDeclaredFields();
        for (Field campo : campos) {
            campo.setAccessible(true);
            if (campo.isAnnotationPresent(annotation) && campo.getType().getTypeName().equals(BigDecimal.class.getTypeName())) {
                valor = valor.add((BigDecimal) campo.get(objeto));
            }
        }
        return valor;
    }

    @Override
    public BigDecimal subtrair(Object objeto) throws IllegalAccessException {
        return calcularValoresPorAnnotation(objeto, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object objeto) throws IllegalAccessException {
        return somar(objeto).subtract(subtrair(objeto));
    }
}
