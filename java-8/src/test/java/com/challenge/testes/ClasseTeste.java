package com.challenge.testes;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;

import java.math.BigDecimal;

public class ClasseTeste {

    @Somar
    public Double valor1 = 10.0;

    @Somar
    public BigDecimal valor2 = BigDecimal.TEN;

    public BigDecimal valor3 = BigDecimal.TEN;

    @Subtrair
    public Double valor4 = 10.0;

    @Subtrair
    public BigDecimal valor5 = BigDecimal.TEN;


    public BigDecimal valor6 = BigDecimal.TEN;
}
