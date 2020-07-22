package com.shopping.simpleadmin.ifs;

import com.shopping.simpleadmin.model.network.Header;

public interface CrudInterface {

    Header create();

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
