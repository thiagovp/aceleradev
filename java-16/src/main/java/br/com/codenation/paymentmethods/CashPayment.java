package br.com.codenation.paymentmethods.impl;

import br.com.codenation.paymentmethods.PriceStrategy;

public class CashPayment implements PriceStrategy {
    @Override
    public Double calculate(Double price) {
        return price * 0.9;
    }
}
