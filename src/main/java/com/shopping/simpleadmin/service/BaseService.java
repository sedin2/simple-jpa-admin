package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Request, Response, Entity> implements CrudInterface<Request, Response> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
}
