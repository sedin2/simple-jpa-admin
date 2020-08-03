package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.OrderDetailApiRequest;
import com.shopping.simpleadmin.model.network.response.OrderDetailApiResponse;
import com.shopping.simpleadmin.service.OrderDetailAPiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailApiController implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailAPiService orderDetailAPiService;

    @Override
    @PostMapping("")
    public Header<OrderDetailApiResponse> create(@RequestBody Header<OrderDetailApiRequest> request) {
        return orderDetailAPiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderDetailApiResponse> read(@PathVariable(name = "id") Long id) {
        return orderDetailAPiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderDetailApiResponse> update(@RequestBody Header<OrderDetailApiRequest> request) {
        return orderDetailAPiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<OrderDetailApiResponse> delete(@PathVariable(name = "id") Long id) {
        return orderDetailAPiService.delete(id);
    }
}
