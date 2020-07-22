package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.OrderGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class OrderGroupRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    @Transactional
    public void create() {
        OrderGroup orderGroup = OrderGroup.builder()
                                          .status("Complete")
                                          .orderType("All")
                                          .revAddress("서울시 강남구")
                                          .revName("홍길동")
                                          .paymentType("Card")
                                          .totalPrice(BigDecimal.valueOf(900000))
                                          .totalQuantity(1)
                                          .orderAt(LocalDateTime.now().minusDays(2))
                                          .arrivalDate(LocalDateTime.now())
                                          .createdAt(LocalDateTime.now())
                                          .createdBy("AdminServer")
                                          .userId(1L)
                                          .build();
        assertThat(orderGroupRepository.save(orderGroup), is(notNullValue()));
    }

    @Test
    @Transactional
    public void read() {

    }

}