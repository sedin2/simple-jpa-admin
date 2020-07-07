package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.model.entitiy.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
