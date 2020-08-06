package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.network.request.ItemApiRequest;
import com.shopping.simpleadmin.model.network.response.ItemApiResponse;
import com.shopping.simpleadmin.service.ItemApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/items")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiService itemApiService;

    @PostConstruct
    public void init() {
        this.baseService = itemApiService;
    }
}
