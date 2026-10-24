package kz.com.ecommerce.strategy;
import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal applyDiscount(BigDecimal price);
}