package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.SimpleAdminApplicationTests;
import com.shopping.simpleadmin.model.entitiy.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PartnerRepositoryTest extends SimpleAdminApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    @Transactional
    public void create() {
        Partner partner = Partner.builder()
                                 .name("Partner01")
                                 .status("Registered")
                                 .address("서울시 강남구")
                                 .callCenter("031-0000-1111")
                                 .partnerNumber("010-1111-2222")
                                 .businessNumber("123123123123")
                                 .ceoName("홍길동")
                                 .registeredAt(LocalDateTime.now())
                                 .createdAt(LocalDateTime.now())
                                 .createdBy("AdminServer")
                                 .categoryId(1L)
                                 .build();

        Partner newPartner = partnerRepository.save(partner);
        assertThat(newPartner.getName(), equalTo("Partner01"));
    }

    @Test
    @Transactional
    public void read() {
        Partner partner = Partner.builder()
                                 .name("Partner01")
                                 .status("Registered")
                                 .address("서울시 강남구")
                                 .callCenter("031-0000-1111")
                                 .partnerNumber("010-1111-2222")
                                 .businessNumber("123123123123")
                                 .ceoName("홍길동")
                                 .registeredAt(LocalDateTime.now())
                                 .createdAt(LocalDateTime.now())
                                 .createdBy("AdminServer")
                                 .categoryId(1L)
                                 .build();

        Partner newPartner = partnerRepository.save(partner);
        Optional<Partner> foundPartner = partnerRepository.findByName("Partner01");
        foundPartner.ifPresent(p -> {
            assertThat(newPartner, equalTo(p));
        });
    }
}