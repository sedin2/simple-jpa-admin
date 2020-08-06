package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.entitiy.Item;
import com.shopping.simpleadmin.model.network.request.ItemApiRequest;
import com.shopping.simpleadmin.model.network.response.ItemApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/items")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
