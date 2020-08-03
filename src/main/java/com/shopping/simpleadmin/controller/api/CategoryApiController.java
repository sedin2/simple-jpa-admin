package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.CategoryApiRequest;
import com.shopping.simpleadmin.model.network.response.CategoryApiResponse;
import com.shopping.simpleadmin.service.CategoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryApiService categoryApiService;

    @Override
    @PostMapping("")
    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
        return categoryApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryApiResponse> read(@PathVariable(name = "id") Long id) {
        return categoryApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
        return categoryApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<CategoryApiResponse> delete(@PathVariable(name = "id") Long id) {
        return categoryApiService.delete(id);
    }
}
