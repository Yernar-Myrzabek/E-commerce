package kz.com.ecommerce.decorator;
import java.math.BigDecimal;

public interface OrderComponent {
    BigDecimal getPrice();
    String getDescription();
}