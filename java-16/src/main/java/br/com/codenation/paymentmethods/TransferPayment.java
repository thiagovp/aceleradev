package br.com.codenation.paymentmethods.impl;

import br.com.codenation.paymentmethods.PriceStrategy;

public class TransferPayment implements PriceStrategy {
    @Override
    public Double calculate(Double price) {
        return price * 0.92;
    }
}
