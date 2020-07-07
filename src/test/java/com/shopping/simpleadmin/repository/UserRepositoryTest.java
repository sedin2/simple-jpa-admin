package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void create() {
        User user = User.builder()
                        .account("TestUser01")
                        .email("TestUser01@mail.com")
                        .phoneNumber("010-1111-2222")
                        .createdAt(LocalDateTime.now())
                        .createdBy("TestUser01")
                        .build();
        userRepository.save(user);
    }

    @Test
    public void read() {
        User user = User.builder()
                        .account("TestUser01")
                        .email("TestUser01@mail.com")
                        .phoneNumber("010-1111-2222")
                        .createdAt(LocalDateTime.now())
                        .createdBy("TestUser01")
                        .build();
        userRepository.save(user);

        Optional<User> newUser = userRepository.findById(1L);

        newUser.ifPresent(selectedUser -> {
            selectedUser.getOrderDetailList().stream().forEach(orderDetail -> {
                System.out.println(orderDetail.getItem());
            });
        });
    }

    @Test
    @Transactional
    public void update() {
        User user = new User();
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@mail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser01");
        userRepository.save(user);

        Optional<User> newUser = userRepository.findById(1L);

        newUser.ifPresent(selectUser -> {
            selectUser.setAccount("aaaa");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("UpdateUser");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        User user = new User();
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@mail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser01");
        userRepository.save(user);

        Optional<User> newUser = userRepository.findById(1L);
        assertThat(newUser.isPresent(), is(equalTo(true)));
        newUser.ifPresent(selectUser -> userRepository.delete(selectUser));
        Optional<User> deleteUser = userRepository.findById(1L);
        assertThat(deleteUser.isPresent(), is(equalTo(false)));
    }
}