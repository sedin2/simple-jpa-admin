package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.model.entitiy.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
