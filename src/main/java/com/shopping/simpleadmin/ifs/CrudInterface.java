package com.shopping.simpleadmin.ifs;

import com.shopping.simpleadmin.model.network.Header;

public interface CrudInterface<Request, Response> {

    Header<Response> create(Header<Request> request);

    Header<Response> read(Long id);

    Header<Response> update(Header<Request> request);

    Header<Response> delete(Long id);
}
