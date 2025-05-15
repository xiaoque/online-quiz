package CodingGames.design_pattern;/*
 *  @author xiaoque
 *  @create 2025-05-15-21:52
 *  @description
 */

import java.math.BigDecimal;
/**
 * Encapsulate a specific behaviour by using interface, and implement different strategies later.
 * Provide flexibility to modify / add strategies.
 */
public class Strategy {
    class Transaction {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void performPayment(BigDecimal amount) {
            paymentStrategy.pay(amount);
        }
    }

    interface PaymentStrategy {
        void pay(BigDecimal amount);
    }

    class CreditCard implements PaymentStrategy {
        @Override
        public void pay(BigDecimal amount) {
            // payment by card
        }
    }

    class Wallet implements PaymentStrategy {
        @Override
        public void pay(BigDecimal amount) {
            // payment by cash
        }
    }

}
