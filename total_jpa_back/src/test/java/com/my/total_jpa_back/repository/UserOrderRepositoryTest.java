package com.my.total_jpa_back.repository;

import com.my.total_jpa_back.entity.OrderStatus;
import com.my.total_jpa_back.entity.UserOrder;
import com.my.total_jpa_back.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserOrderRepositoryTest {
    @Autowired
    UserOrderRepository userOrderRepository;

    @Test
    @DisplayName("전체 주문 조회")
    void findAll() {
        // given

        // when
        List<UserOrder> orders = userOrderRepository.findAll();
        // then
        assertThat(orders.size()).isEqualTo(5000);
    }

    // 2번
    @Test
    @DisplayName("findByStatus")
    void findByStatus() {
        List<UserOrder> orders = userOrderRepository
                .findByStatus(OrderStatus.COMPLETE);
        for (UserOrder order : orders) {
            log.info("orderId = {}, status = {}", order.getId(), order.getStatus());
        }
    }


    // 3번
    @Test
    @DisplayName("상품명에 Dunk가 포함된 주문을 조회")
    void findByProductNameContaining() {
        List<UserOrder> orders = userOrderRepository.findByProductNameContaining("Dunk");
        for (UserOrder order : orders) {
            log.info("name = {}, productName = {}", order.getId(), order.getProductName());
        }
    }

    // 4번
    @Test
    @DisplayName("findByPriceGreaterThanEqual")
    void findByPriceGreaterThanEqual() {
        List<UserOrder> orders = userOrderRepository
                .findByPriceGreaterThanEqual(300000);
        for (UserOrder order : orders) {
            log.info("name = {}, productName = {}", order.getId(), order.getPrice());
        }
    }
    // 5번
    @Test
    @DisplayName("findByUserId")
    void findByUserId() {
        List<UserOrder> orders = userOrderRepository
                .findByUserId(1L);
        for (UserOrder order : orders) {
            log.info("userId = {}, productName = {}", order.getId(), order.getUserId(), order.getProductName());
        }
    }
    // 6번
    @Test
    @DisplayName("findByUserIdAndStatus")
    void findByUserIdAndStatus() {
        List<UserOrder> orders = userOrderRepository
                .findByUserIdAndStatus(10L, OrderStatus.COMPLETE);
        for (UserOrder order : orders) {
            log.info("userId = {}, status = {}", order.getId(), order.getStatus());
        }
    }
    // 7번
    @Test
    @DisplayName("findByPriceBetween")
    void findByPriceBetween() {
        List<UserOrder> orders = userOrderRepository
                .findByPriceBetween(290000, 300000);
        for (UserOrder order : orders) {
            log.info("productName = {}, price = {}", order.getProductName(), order.getPrice());
        }
    }

    // 8번
    @Test
    @DisplayName("findAllByOrderByPriceDesc")
    void findAllByOrderByPriceDesc() {
        List<UserOrder> orders = userOrderRepository
                .findAllByOrderByPriceDesc();
        for (int i = 1; i <= 5; i ++){
            log.info("productName = {}, price = {}", orders.get(i).getProductName(),
                    orders.get(i).getPrice());
        }
    }

    @Test
    @DisplayName("findTop5ByOrderByCreatedAtDesc")
    void findTop5ByOrderByCreatedAtDesc() {
        List<UserOrder> orders = userOrderRepository
                .findTop5ByOrderByCreatedAtDesc();
        for (UserOrder order : orders) {
            log.info("productName = {}, createdAt = {}", order.getProductName(), order.getCreatedAt());
        }
    }

    @Test
    @DisplayName("findByStatusIn")
    void findByStatusIn() {
        List<OrderStatus> orderStatusList = new ArrayList<>(
                List.of(OrderStatus.READY, OrderStatus.SHIPPING)
        );
        List<UserOrder> orders = userOrderRepository
                .findByStatusIn();
        for (UserOrder order : orders) {
            log.info("orderId = {}, status = {}", order.getId(), order.getStatus());
        }
    }
}