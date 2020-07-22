package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CategoryRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void create() {
        Category category = Category.builder()
                                    .type("Computer")
                                    .title("컴퓨터")
                                    .createdAt(LocalDateTime.now())
                                    .createdBy("AdminUser")
                                    .build();
        assertThat(categoryRepository.save(category), is(notNullValue()));
    }

    @Test
    public void read() {
        String type = "Computer";
        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        optionalCategory.ifPresent(category -> {
            assertThat(category.getType(), equalTo(type));
        });
    }
}