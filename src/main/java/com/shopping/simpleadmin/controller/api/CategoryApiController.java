package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.network.request.CategoryApiRequest;
import com.shopping.simpleadmin.model.network.response.CategoryApiResponse;
import com.shopping.simpleadmin.service.CategoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryApiService categoryApiService;

    @PostConstruct
    public void init() {
        this.baseService = categoryApiService;
    }
}
