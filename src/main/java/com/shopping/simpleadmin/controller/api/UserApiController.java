package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.UserApiRequest;
import com.shopping.simpleadmin.model.network.response.UserApiResponse;
import com.shopping.simpleadmin.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiService userApiService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("{}", id);
        return userApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<UserApiResponse> delete(@PathVariable(name = "id") Long id) {
        log.info("{}", id);
        return userApiService.delete(id);
    }
}
