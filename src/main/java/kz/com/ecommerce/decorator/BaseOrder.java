package kz.com.ecommerce.decorator;
import java.math.BigDecimal;

public class BaseOrder implements OrderComponent {
    private final BigDecimal basePrice;

    public BaseOrder(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public BigDecimal getPrice() { return basePrice; }

    @Override
    public String getDescription() { return "Standard Order"; }
}