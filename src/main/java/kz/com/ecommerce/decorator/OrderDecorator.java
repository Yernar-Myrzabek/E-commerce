package kz.com.ecommerce.decorator;
import java.math.BigDecimal;

public abstract class OrderDecorator implements OrderComponent {
    protected final OrderComponent wrappedOrder;

    public OrderDecorator(OrderComponent wrappedOrder) {
        this.wrappedOrder = wrappedOrder;
    }

    @Override
    public BigDecimal getPrice() { return wrappedOrder.getPrice(); }

    @Override
    public String getDescription() { return wrappedOrder.getDescription(); }
}