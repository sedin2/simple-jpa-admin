package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.controller.CrudController;
import com.shopping.simpleadmin.model.network.request.PartnerApiRequest;
import com.shopping.simpleadmin.model.network.response.PartnerApiResponse;
import com.shopping.simpleadmin.service.PartnerApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/partners")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerApiService partnerApiService;

    @PostConstruct
    public void init() {
        this.baseService = partnerApiService;
    }
}
