package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.model.entitiy.User;
import com.shopping.simpleadmin.model.enumclass.UserStatus;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.UserApiRequest;
import com.shopping.simpleadmin.model.network.response.UserApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserApiService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        User user = User.builder()
                        .account(userApiRequest.getAccount())
                        .password(userApiRequest.getPassword())
                        .status(UserStatus.REGISTERED)
                        .email(userApiRequest.getEmail())
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .registeredAt(LocalDateTime.now())
                        .build();
        User newUser = baseRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                             .map(user -> response(user))
                             .orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        return baseRepository.findById(userApiRequest.getId())
                             .map(user -> {
                                 user.setAccount(user.getAccount())
                                     .setPassword(userApiRequest.getPassword())
                                     .setStatus(userApiRequest.getStatus())
                                     .setPhoneNumber(userApiRequest.getPhoneNumber())
                                     .setEmail(userApiRequest.getEmail())
                                     .setRegisteredAt(userApiRequest.getRegisteredAt())
                                     .setUnregisteredAt(userApiRequest.getUnregisteredAt());
                                 return user;
                             })
                             .map(user -> baseRepository.save(user))
                             .map(updatedUser -> response(updatedUser))
                             .orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                             .map(user -> {
                                 baseRepository.delete(user);
                                 return Header.OK();
                             })
                             .orElseGet(() -> Header.ERROR("Not Existed Data"));
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
