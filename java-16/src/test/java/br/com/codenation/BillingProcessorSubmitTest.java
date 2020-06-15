package br.com.codenation;

import br.com.codenation.paymentmethods.PaymentMethod;
import br.com.codenation.paymentmethods.PriceStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;

public class BillingProcessorSubmitTest {

    private BillingProcessor billingProcessor = new BillingProcessor();

    @Test
    public void testCashPayment() throws NoSuchFieldException {
        Assert.assertNotNull(PaymentMethod.CASH.getPaymentStrategy());
        PaymentMethod paymentMethod = mockPaymentMethod(PaymentMethod.CASH);
        Assert.assertEquals(Double.valueOf(90.0), this.billingProcessor.calculate(new Order(100.0, paymentMethod)));
        Mockito.verify(paymentMethod.getPaymentStrategy(), Mockito.times(1)).calculate(100.0);
    }

    @Test
    public void testCreditCardPayment() throws NoSuchFieldException {
        Assert.assertNotNull(PaymentMethod.CREDIT_CARD.getPaymentStrategy());
        PaymentMethod paymentMethod = mockPaymentMethod(PaymentMethod.CREDIT_CARD);
        Assert.assertEquals(Double.valueOf(98.0), this.billingProcessor.calculate(new Order(100.0, PaymentMethod.CREDIT_CARD)));
        Mockito.verify(paymentMethod.getPaymentStrategy(), Mockito.times(1)).calculate(Matchers.any());
    }

    @Test
    public void testDebitCardPayment() throws NoSuchFieldException {
        Assert.assertNotNull(PaymentMethod.DEBIT_CARD.getPaymentStrategy());
        PaymentMethod paymentMethod = mockPaymentMethod(PaymentMethod.DEBIT_CARD);
        Assert.assertEquals(Double.valueOf(95.0), this.billingProcessor.calculate(new Order(100.0, PaymentMethod.DEBIT_CARD)));
        Mockito.verify(paymentMethod.getPaymentStrategy(), Mockito.times(1)).calculate(Matchers.any());
    }

    @Test
    public void testTransferPayment() throws NoSuchFieldException {
        Assert.assertNotNull(PaymentMethod.TRANSFER.getPaymentStrategy());
        PaymentMethod paymentMethod = mockPaymentMethod(PaymentMethod.TRANSFER);
        Assert.assertEquals(Double.valueOf(92.0), this.billingProcessor.calculate(new Order(100.0, PaymentMethod.TRANSFER)));
        Mockito.verify(paymentMethod.getPaymentStrategy(), Mockito.times(1)).calculate(Matchers.any());
    }

    private PaymentMethod mockPaymentMethod(PaymentMethod paymentMethod) throws NoSuchFieldException {
        PriceStrategy strategy = (PriceStrategy) Mockito.mock(findImplementation(paymentMethod));
        Mockito.when(strategy.calculate(Matchers.anyDouble())).thenCallRealMethod();
        Field f = paymentMethod.getClass().getDeclaredField("priceStrategy"); //NoSuchFieldException
        f.setAccessible(true);
        Whitebox.setInternalState(paymentMethod, "priceStrategy", strategy);
        return paymentMethod;
    }

    private Class findImplementation(PaymentMethod paymentMethod) {
        Reflections reflections = new Reflections("br.com.codenation.paymentmethods");
        Set<Class<? extends PriceStrategy>> classes = reflections.getSubTypesOf(PriceStrategy.class);
        return classes.stream().filter(cls -> cls.getName().toUpperCase().contains(paymentMethod.name().replace("_", ""))).findAny().orElseThrow(() -> new RuntimeException("No implementation of PriceStrategy was found containing " + paymentMethod.name().toLowerCase().replace("_", "") + " in the class name."));
    }

}
