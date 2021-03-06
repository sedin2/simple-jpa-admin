package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.model.entitiy.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
