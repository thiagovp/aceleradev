package com.challenge.interfaces;

import java.math.BigDecimal;

public interface Calculavel {

    BigDecimal somar(Object objeto) throws IllegalAccessException;
    BigDecimal subtrair(Object objeto) throws IllegalAccessException;
    BigDecimal totalizar(Object objeto) throws IllegalAccessException;
}
