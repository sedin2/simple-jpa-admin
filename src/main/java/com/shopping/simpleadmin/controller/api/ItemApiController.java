package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.ItemApiRequest;
import com.shopping.simpleadmin.model.network.response.ItemApiResponse;
import com.shopping.simpleadmin.service.ItemApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/items")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiService itemApiService;

    @Override
    @PostMapping("")
    public Header create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header read(@PathVariable(name = "id") Long id) {
        return itemApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name = "id") Long id) {
        return itemApiService.delete(id);
    }
}
