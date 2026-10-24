package kz.com.ecommerce.service;

import kz.com.ecommerce.adapter.PaymentProcessor;
import kz.com.ecommerce.decorator.BaseOrder;
import kz.com.ecommerce.decorator.GiftWrapDecorator;
import kz.com.ecommerce.decorator.OrderComponent;
import kz.com.ecommerce.entity.Order;
import kz.com.ecommerce.entity.User;
import kz.com.ecommerce.repository.OrderRepository;
import kz.com.ecommerce.strategy.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentProcessor paymentProcessor;

    public OrderService(OrderRepository orderRepository, PaymentProcessor paymentProcessor) {
        this.orderRepository = orderRepository;
        this.paymentProcessor = paymentProcessor;
    }

    public Order placeOrder(User user, BigDecimal cartTotal, DiscountStrategy discountStrategy, boolean addGiftWrap) {
        BigDecimal discountedTotal = discountStrategy.applyDiscount(cartTotal);

        OrderComponent orderDetails = new BaseOrder(discountedTotal);
        if (addGiftWrap) {
            orderDetails = new GiftWrapDecorator(orderDetails);
        }
        BigDecimal finalPrice = orderDetails.getPrice();

        boolean paymentSuccess = paymentProcessor.processPayment(finalPrice);
        if (!paymentSuccess) {
            throw new RuntimeException("Payment processing failed");
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(finalPrice);
        order.setStatus("PAID - " + orderDetails.getDescription());
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }
}