package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.network.request.OrderDetailApiRequest;
import com.shopping.simpleadmin.model.network.response.OrderDetailApiResponse;
import com.shopping.simpleadmin.service.OrderDetailAPiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailAPiService orderDetailAPiService;

    @PostConstruct
    public void init() {
        this.baseService = orderDetailAPiService;
    }
}
