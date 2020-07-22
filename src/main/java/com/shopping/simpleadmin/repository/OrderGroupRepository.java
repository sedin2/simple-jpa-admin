package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.model.entitiy.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {

}
