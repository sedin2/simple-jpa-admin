package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(userRepository.save(user), is(notNullValue()));
    }

    @Test
    @Transactional
    public void read() {
        User user = User.builder()
                        .account("TestUser01")
                        .email("TestUser01@mail.com")
                        .phoneNumber("010-1111-2222")
                        .createdAt(LocalDateTime.now())
                        .createdBy("TestUser01")
                        .build();
        userRepository.save(user);

        Optional<User> newUser = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        newUser.ifPresent(selectedUser -> {
            selectedUser.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------주문묶음------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("-----------주문상세------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
                });
            });
        });
    }

    @Test
    @Transactional
    public void update() {
        User user = User.builder()
                        .account("TestUser01")
                        .email("TestUser01@mail.com")
                        .phoneNumber("010-1111-2222")
                        .createdAt(LocalDateTime.now())
                        .createdBy("TestUser01")
                        .build();
        assertThat(userRepository.save(user), is(notNullValue()));

        Optional<User> newUser = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        newUser.ifPresent(selectUser -> {
            selectUser.setAccount("aaaa");

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
        newUser.ifPresent(u -> {
            assertThat(u.getId(), equalTo(1L));
        });

        Optional<User> deleteUser = userRepository.findById(1L);
        assertThat(deleteUser.isPresent(), is(equalTo(false)));
    }
}