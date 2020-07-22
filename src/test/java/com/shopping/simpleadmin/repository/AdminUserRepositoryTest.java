package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class AdminUserRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    @Transactional
    public void create() {
        AdminUser adminUser = AdminUser.builder()
                                       .account("AdminUser01")
                                       .password("AdminUserPassword")
                                       .status("Registered")
                                       .role("Partner")
                                       .createdAt(LocalDateTime.now())
                                       .createdBy("AdminServer")
                                       .build();
        assertThat(adminUserRepository.save(adminUser), is(notNullValue()));
    }

    @Test
    @Transactional
    public void read() {

    }

}