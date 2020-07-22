package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    public void create() {
        Item item = Item.builder()
                        .status("Unregistered")
                        .name("노트북")
                        .title("삼성 노트북 A100")
                        .brandName("삼성")
                        .price(100000)
                        .content("SAMSUNG NOTEBOOK")
                        .registeredAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .createdBy("Partner01")
                        .partnerId(1L)
                        .build();
        Assert.notNull(itemRepository.save(item));
    }

    @Test
    @Transactional
    public void read() {
        Item item = Item.builder()
                        .name("노트북")
                        .price(100000)
                        .content("SAMSUNG NOTEBOOK")
                        .build();
        Assert.notNull(itemRepository.save(item));

        Optional<Item> selectedItem = itemRepository.findById(1L);

        Assert.isTrue(selectedItem.isPresent());
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
