package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.entitiy.User;
import com.shopping.simpleadmin.model.enumclass.UserStatus;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.UserApiRequest;
import com.shopping.simpleadmin.model.network.response.UserApiResponse;
import com.shopping.simpleadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
                        .status(UserStatus.REGISTERED)
                        .email(userApiRequest.getEmail())
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .registeredAt(LocalDateTime.now())
                        .build();
        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id)
                             .map(user -> response(user))
                             .orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        Optional<User> optionalUser = userRepository.findById(userApiRequest.getId());
        return optionalUser.map(user -> {
            user.setAccount(user.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        }).map(user -> userRepository.save(user))
          .map(updatedUser -> response(updatedUser))
          .orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
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
