package kz.com.ecommerce.adapter;

import org.springframework.stereotype.Component;

@Component
public class ExternalBankApi {
    public boolean chargeCard(double total) {
        System.out.println("Processing payment of $" + total + " through External Bank API...");
        return true;
    }
}