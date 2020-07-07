package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.Item;
import com.shopping.simpleadmin.model.entitiy.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    @Transactional
    public void create() {
//        Item item = Item.builder()
//                .name("노트북")
//                .price(100000)
//                .content("SAMSUNG NOTEBOOK")
//                .build();
//        Assert.notNull(itemRepository.save(item));

        OrderDetail orderDetail = OrderDetail.builder()
                                             .orderAt(LocalDateTime.now())
                                             .build();
        Assert.notNull(orderDetailRepository.save(orderDetail));
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
