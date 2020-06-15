package br.com.codenation.paymentmethods;

import br.com.codenation.paymentmethods.PriceStrategy;

public class DebitCardPayment implements PriceStrategy {

    public static final double DISCOUNT = 0.95;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT;
    }
}
