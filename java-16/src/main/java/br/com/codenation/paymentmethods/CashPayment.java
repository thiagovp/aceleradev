package br.com.codenation.paymentmethods;

import br.com.codenation.paymentmethods.PriceStrategy;

public class CashPayment implements PriceStrategy {

    public static final double DISCOUNT = 0.9;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT;
    }
}
