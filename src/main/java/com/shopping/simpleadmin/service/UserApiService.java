package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.entitiy.User;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.UserApiRequest;
import com.shopping.simpleadmin.model.network.response.UserApiResponse;
import com.shopping.simpleadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserApiService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        User user = User.builder()
                        .account(userApiRequest.getAccount())
                        .password(userApiRequest.getPassword())
                        .status("Registered")
                        .email(userApiRequest.getEmail())
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .registeredAt(LocalDateTime.now())
                        .build();
        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header<UserApiResponse> delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                                                         .id(user.getId())
                                                         .account(user.getAccount())
                                                         .password(user.getPassword())
                                                         .email(user.getEmail())
                                                         .phoneNumber(user.getPhoneNumber())
                                                         .status(user.getStatus())
                                                         .registeredAt(user.getRegisteredAt())
                                                         .unregisteredAt(user.getUnregisteredAt())
                                                         .build();
        return Header.OK(userApiResponse);
    }
}
