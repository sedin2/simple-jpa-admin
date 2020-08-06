package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.network.request.OrderGroupApiRequest;
import com.shopping.simpleadmin.model.network.response.OrderGroupApiResponse;
import com.shopping.simpleadmin.service.OrderGroupApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orders")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupApiService orderGroupApiService;

    @PostConstruct
    public void init() {
        this.baseService = orderGroupApiService;
    }
}
