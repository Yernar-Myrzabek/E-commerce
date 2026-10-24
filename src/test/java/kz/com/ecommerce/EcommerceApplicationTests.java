package kz.com.ecommerce;

import kz.com.ecommerce.adapter.BankPaymentAdapter;
import kz.com.ecommerce.adapter.ExternalBankApi;
import kz.com.ecommerce.decorator.BaseOrder;
import kz.com.ecommerce.decorator.GiftWrapDecorator;
import kz.com.ecommerce.decorator.OrderComponent;
import kz.com.ecommerce.strategy.FixedDiscountStrategy;
import kz.com.ecommerce.strategy.PercentageDiscountStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EcommerceApplicationTests {

	@Test
	void testPercentageDiscountStrategy() {
		// Проверяем, что скидка 20% от $100 дает $80
		PercentageDiscountStrategy strategy = new PercentageDiscountStrategy(new BigDecimal("20.00"));
		BigDecimal result = strategy.applyDiscount(new BigDecimal("100.00"));
		assertEquals(new BigDecimal("80.0000"), result);
	}

	@Test
	void testFixedDiscountStrategy() {
		// Проверяем, что фиксированная скидка $15 от $50 дает $35
		FixedDiscountStrategy strategy = new FixedDiscountStrategy(new BigDecimal("15.00"));
		BigDecimal result = strategy.applyDiscount(new BigDecimal("50.00"));
		assertEquals(new BigDecimal("35.00"), result);
	}

	@Test
	void testDecoratorPatternAddsPriceAndDescription() {
		// Проверяем базовый заказ на $50
		OrderComponent order = new BaseOrder(new BigDecimal("50.00"));

		// Обертываем его в подарочную упаковку (+$10)
		order = new GiftWrapDecorator(order);

		assertEquals(new BigDecimal("60.00"), order.getPrice());
		assertTrue(order.getDescription().contains("Gift Wrap"));
	}

	@Test
	void testAdapterPatternProcessesPayment() {
		// Проверяем успешную адаптацию внешнего API банка
		ExternalBankApi externalApi = new ExternalBankApi();
		BankPaymentAdapter adapter = new BankPaymentAdapter(externalApi);

		boolean isSuccess = adapter.processPayment(new BigDecimal("99.99"));
		assertTrue(isSuccess);
	}

	@Test
	void testErrorHandlingForNegativePrices() {
		// Проверяем, что фиксированная скидка, превышающая цену товара, не уводит баланс в минус
		FixedDiscountStrategy strategy = new FixedDiscountStrategy(new BigDecimal("100.00"));
		BigDecimal result = strategy.applyDiscount(new BigDecimal("50.00"));

		// Ожидаем $0.00, а не -$50.00
		assertEquals(BigDecimal.ZERO, result);
	}
}