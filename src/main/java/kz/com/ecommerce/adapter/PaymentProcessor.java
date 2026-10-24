package kz.com.ecommerce.adapter;
import java.math.BigDecimal;

public interface PaymentProcessor {
    boolean processPayment(BigDecimal amount);
}