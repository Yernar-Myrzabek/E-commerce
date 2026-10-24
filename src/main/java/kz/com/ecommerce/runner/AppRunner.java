package kz.com.ecommerce.runner;

import kz.com.ecommerce.entity.User;
import kz.com.ecommerce.repository.UserRepository;
import kz.com.ecommerce.service.OrderService;
import kz.com.ecommerce.strategy.DiscountStrategy;
import kz.com.ecommerce.strategy.PercentageDiscountStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AppRunner implements CommandLineRunner {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public AppRunner(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("========== E-COMMERCE SYSTEM STARTED ==========");

        // 1. Создаем тестового пользователя
        User testUser = new User();
        testUser.setUsername("Yernar");
        testUser.setPassword("password123");
        testUser.setRole("CUSTOMER");
        testUser.setEmail("yernar@sdu.edu.kz");
        userRepository.save(testUser);

        // 2. Симулируем корзину на сумму $100
        BigDecimal cartTotal = new BigDecimal("100.00");
        System.out.println("Cart Total: $" + cartTotal);

        // 3. Выбираем стратегию скидки (STRATEGY PATTERN: скидка 15%)
        DiscountStrategy blackFridayDiscount = new PercentageDiscountStrategy(new BigDecimal("15.00"));

        // 4. Оформляем заказ (внутри сработают DECORATOR на упаковку и ADAPTER на оплату)
        System.out.println("Placing order with 15% discount and Gift Wrap...");
        var order = orderService.placeOrder(testUser, cartTotal, blackFridayDiscount, true);

        // 5. Выводим результат
        System.out.println("========== ORDER SUCCESSFULLY PLACED ==========");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Total Paid: $" + order.getTotalPrice());
        System.out.println("Status: " + order.getStatus());
        System.out.println("===============================================");
    }
}