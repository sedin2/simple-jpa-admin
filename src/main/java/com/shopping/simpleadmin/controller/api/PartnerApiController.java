package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.PartnerApiRequest;
import com.shopping.simpleadmin.model.network.response.PartnerApiResponse;
import com.shopping.simpleadmin.service.PartnerApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerApiService partnerApiService;

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable(name = "id") Long id) {
        return partnerApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        return partnerApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<PartnerApiResponse> delete(@PathVariable(name = "id") Long id) {
        return partnerApiService.delete(id);
    }
}
