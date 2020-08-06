package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.entitiy.User;
import com.shopping.simpleadmin.model.network.request.UserApiRequest;
import com.shopping.simpleadmin.model.network.response.UserApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

}
