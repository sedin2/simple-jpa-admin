package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.model.entitiy.OrderGroup;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.OrderGroupApiRequest;
import com.shopping.simpleadmin.model.network.response.OrderGroupApiResponse;
import com.shopping.simpleadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class OrderGroupApiService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                                          .status(orderGroupApiRequest.getStatus())
                                          .orderType(orderGroupApiRequest.getOrderType())
                                          .revAddress(orderGroupApiRequest.getRevAddress())
                                          .revName(orderGroupApiRequest.getRevName())
                                          .paymentType(orderGroupApiRequest.getPaymentType())
                                          .totalPrice(orderGroupApiRequest.getTotalPrice())
                                          .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                                          .orderAt(LocalDateTime.now())
                                          .arrivalDate(LocalDateTime.now().plusDays(3L))
                                          .user(userRepository.findById(orderGroupApiRequest.getUserId()).orElseThrow(NoSuchElementException::new))
                                          .build();
        OrderGroup newOrderGroup = baseRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                             .map(orderGroup -> response(orderGroup))
                             .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        return baseRepository.findById(orderGroupApiRequest.getId())
                             .map(entityOrderGroup -> entityOrderGroup.setStatus(orderGroupApiRequest.getStatus())
                                                                      .setOrderType(orderGroupApiRequest.getOrderType())
                                                                      .setRevAddress(orderGroupApiRequest.getRevAddress())
                                                                      .setRevName(orderGroupApiRequest.getRevName())
                                                                      .setPaymentType(orderGroupApiRequest.getPaymentType())
                                                                      .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                                                                      .setTotalQuantity(orderGroupApiRequest.getTotalQuantity())
                                                                      .setOrderAt(orderGroupApiRequest.getOrderAt())
                                                                      .setArrivalDate(orderGroupApiRequest.getArrivalDate())
                                                                      .setUser(userRepository.findById(orderGroupApiRequest.getUserId()).orElseThrow(NoSuchElementException::new)))
                             .map(newOrderGroup -> baseRepository.save(newOrderGroup))
                             .map(orderGroup -> response(orderGroup))
                             .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                             .map(orderGroup -> {
                                 baseRepository.delete(orderGroup);
                                 return Header.OK();
                             })
                             .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                                                                           .id(orderGroup.getId())
                                                                           .status(orderGroup.getStatus())
                                                                           .orderType(orderGroup.getOrderType())
                                                                           .revAddress(orderGroup.getRevAddress())
                                                                           .revName(orderGroup.getRevName())
                                                                           .paymentType(orderGroup.getPaymentType())
                                                                           .totalPrice(orderGroup.getTotalPrice())
                                                                           .totalQuantity(orderGroup.getTotalQuantity())
                                                                           .orderAt(orderGroup.getOrderAt())
                                                                           .arrivalDate(orderGroup.getArrivalDate())
                                                                           .userId(orderGroup.getUser().getId())
                                                                           .build();
        return Header.OK(orderGroupApiResponse);
    }
}
