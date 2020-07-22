package com.shopping.simpleadmin.repository;

import com.shopping.simpleadmin.model.entitiy.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

}
