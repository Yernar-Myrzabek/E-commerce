package kz.com.ecommerce.adapter;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BankPaymentAdapter implements PaymentProcessor {
    private final ExternalBankApi externalApi;

    public BankPaymentAdapter(ExternalBankApi externalApi) {
        this.externalApi = externalApi;
    }

    @Override
    public boolean processPayment(BigDecimal amount) {
        return externalApi.chargeCard(amount.doubleValue());
    }
}