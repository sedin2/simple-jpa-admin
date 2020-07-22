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
        String type = "Computer";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminUser";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        assertThat(newCategory, is(notNullValue()));
        assertThat(newCategory.getType(), equalTo(type));
        assertThat(newCategory.getTitle(), equalTo(title));
        assertThat(newCategory.getCreatedAt(), equalTo(createdAt));
        assertThat(newCategory.getCreatedBy(), equalTo(createdBy));
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