package com.shopping.simpleadmin.controller.api;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {

    @Override
    @PostMapping("")
    public Header create() {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public Header read(@PathVariable(name = "id") Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header update() {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name = "id") Long id) {
        return null;
    }
}
