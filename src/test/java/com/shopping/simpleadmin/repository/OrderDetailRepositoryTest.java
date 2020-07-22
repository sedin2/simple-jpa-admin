package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderDetailRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @Transactional
    public void create() {
        OrderDetail orderDetail = OrderDetail.builder()
                                             .status("Waiting")
                                             .arrivalDate(LocalDate.now().plusDays(2L))
                                             .quantity(1)
                                             .totalPrice(BigDecimal.valueOf(900000))
                                             .createdAt(LocalDateTime.now())
                                             .createdBy("AdminServer")
//                                             .itemId(1L)
//                                             .orderGroupId(1L)
                                             .build();
        assertThat(orderDetailRepository.save(orderDetail), is(notNullValue()));
    }

    @Test
    @Transactional
    public void read() {

    }

    @Test
    @Transactional
    public void update() {

    }

    @Test
    @Transactional
    public void delete() {

    }
}
