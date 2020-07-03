package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@mail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser01");

        User newUser = userRepository.save(user);
        System.out.println("newUser :" + newUser);
    }

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectedUser -> {
            System.out.println("user : " + selectedUser);
            System.out.println("email : " + selectedUser.getEmail());
        });
    }

    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("aaaa");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("UpdateUser");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(2L);
        assertThat(user.isPresent(), is(equalTo(true)));
        user.ifPresent(selectUser -> userRepository.delete(selectUser));
        Optional<User> deleteUser = userRepository.findById(2L);
        assertThat(deleteUser.isPresent(), is(equalTo(false)));
    }
}