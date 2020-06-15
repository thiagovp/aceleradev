package br.com.codenation.paymentmethods;

import br.com.codenation.paymentmethods.PriceStrategy;

public class CreditCardPayment implements PriceStrategy {

    public static final double DISCOUNT = 0.98;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT;
    }
}
