package com.my.total_jpa_back.repository;


import com.my.total_jpa_back.entity.OrderStatus;
import com.my.total_jpa_back.entity.UserOrder;
import com.my.total_jpa_back.entity.Users;
import org.apache.catalina.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    // 1. 전체 주문 조회
    // 2. 주문 상태로 조회 COMPLETE 상태인 주문을 조회
    // select * from user_oder where status = 'COMPLETE'
    List<UserOrder> findByStatus(OrderStatus status);

    // 3. 상품명 포함 검색 : Containing (상품명에 Dunk가 포함된 주문을 조회)
    List<UserOrder> findByProductNameContaining(String keyword);

    // 4. 특정 가격 이상 조회
    // select * from user_order where price >= 300000;
    List<UserOrder> findByPriceGreaterThanEqual(int price);

    // 5. 특정 회원 주문 조회
    // select * from user_order where user_id=1
    List<UserOrder> findByUserId(Long userid);

    // 6. 회원 + 주문상태 조회
    // select * from user_order where
    // userId = 10 and status = 'COMPLETE'
    List<UserOrder> findByUserIdAndStatus(Long userId, OrderStatus status);

    // 7. 가격 범위 조회 : Between
    // select * from user_order where price between 290000 and 300000;
    List<UserOrder> findByPriceBetween(int start, int end);

    // 8. 가격 높은 순 조회 : OrderBy
    // select * from user_order Order by price desc;
    List<UserOrder> findAllByOrderByPriceDesc();

    // 9. 최신 주문 5개 조회
    List<UserOrder> findTop5ByOrderByCreatedAtDesc();

    // 10. 상태 여러 개 조회 :  in
    // in 구문은 리스트로 만들어서 재료를 전달한다.
    // select * from user_order where status in ('READY', 'SHIPPING')
    List<UserOrder> findByStatusIn(List<OrderStatus>list);
}
