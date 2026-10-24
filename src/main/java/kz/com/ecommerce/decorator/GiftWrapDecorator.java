package kz.com.ecommerce.decorator;
import java.math.BigDecimal;

public class GiftWrapDecorator extends OrderDecorator {
    public GiftWrapDecorator(OrderComponent wrappedOrder) {
        super(wrappedOrder);
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal("10.00")); // Упаковка стоит $10
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", + Gift Wrap";
    }
}