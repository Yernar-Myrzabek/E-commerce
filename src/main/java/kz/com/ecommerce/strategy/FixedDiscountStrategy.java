package kz.com.ecommerce.strategy;
import java.math.BigDecimal;

public class FixedDiscountStrategy implements DiscountStrategy {
    private final BigDecimal discountAmount;

    public FixedDiscountStrategy(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        BigDecimal result = price.subtract(discountAmount);
        return result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
    }
}