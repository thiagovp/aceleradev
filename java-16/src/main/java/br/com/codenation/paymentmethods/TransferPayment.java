package br.com.codenation.paymentmethods;

import br.com.codenation.paymentmethods.PriceStrategy;

public class TransferPayment implements PriceStrategy {

    public static final double DISCOUNT = 0.92;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT;
    }
}
