package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

public class ItemRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    public void create() {
        Item item = Item.builder()
                        .name("노트북")
                        .price(100000)
                        .content("SAMSUNG NOTEBOOK")
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
