package kz.com.ecommerce.strategy;
import java.math.BigDecimal;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final BigDecimal percentage;

    public PercentageDiscountStrategy(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        BigDecimal discount = price.multiply(percentage).divide(BigDecimal.valueOf(100));
        return price.subtract(discount);
    }
}