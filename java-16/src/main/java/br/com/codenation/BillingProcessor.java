package br.com.codenation;

import br.com.codenation.paymentmethods.PriceStrategy;

public class BillingProcessor {

    public Double calculate(Order order) {
        PriceStrategy strategy = order.getPaymentMethod().getPaymentStrategy();
        return strategy.calculate(order.getPrice());
    }
}