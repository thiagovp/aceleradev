package br.com.codenation.paymentmethods;

import br.com.codenation.paymentmethods.impl.CashPayment;
import br.com.codenation.paymentmethods.impl.DebitCardPayment;
import br.com.codenation.paymentmethods.impl.TransferPayment;

public enum PaymentMethod {

    CASH(new CashPayment()), DEBIT_CARD(new DebitCardPayment()), CREDIT_CARD(new br.com.codenation.paymentmethods.impl.CreditCardPayment()), TRANSFER(new TransferPayment());

    private final PriceStrategy priceStrategy;

    PaymentMethod(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public PriceStrategy getPaymentStrategy() {
        return priceStrategy;
    }
}