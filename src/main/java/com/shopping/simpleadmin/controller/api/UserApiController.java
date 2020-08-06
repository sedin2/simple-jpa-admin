package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.network.request.UserApiRequest;
import com.shopping.simpleadmin.model.network.response.UserApiResponse;
import com.shopping.simpleadmin.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiService userApiService;

    @PostConstruct
    public void init() {
        this.baseService = userApiService;
    }
}
