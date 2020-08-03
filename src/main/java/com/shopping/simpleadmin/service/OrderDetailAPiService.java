package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.entitiy.OrderDetail;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.OrderDetailApiRequest;
import com.shopping.simpleadmin.model.network.response.OrderDetailApiResponse;
import com.shopping.simpleadmin.repository.ItemRepository;
import com.shopping.simpleadmin.repository.OrderDetailRepository;
import com.shopping.simpleadmin.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderDetailAPiService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest orderDetailApiRequest = request.getData();
        OrderDetail orderDetail = OrderDetail.builder()
                                             .status(orderDetailApiRequest.getStatus())
                                             .arrivalDate(orderDetailApiRequest.getArrivalDate())
                                             .quantity(orderDetailApiRequest.getQuantity())
                                             .totalPrice(orderDetailApiRequest.getTotalPrice())
                                             .item(itemRepository.findById(orderDetailApiRequest.getItemId()).orElseThrow(NoSuchElementException::new))
                                             .orderGroup(orderGroupRepository.findById(orderDetailApiRequest.getOrderGroupId()).orElseThrow(NoSuchElementException::new))
                                             .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return orderDetailRepository.findById(id)
                                    .map(orderDetail -> response(orderDetail))
                                    .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest orderDetailApiRequest = request.getData();
        return orderDetailRepository.findById(orderDetailApiRequest.getId())
                                    .map(entityOrderDetail -> entityOrderDetail.setStatus(orderDetailApiRequest.getStatus())
                                                                               .setArrivalDate(orderDetailApiRequest.getArrivalDate())
                                                                               .setQuantity(orderDetailApiRequest.getQuantity())
                                                                               .setTotalPrice(orderDetailApiRequest.getTotalPrice())
                                                                               .setItem(itemRepository.findById(orderDetailApiRequest.getItemId()).orElseThrow(NoSuchElementException::new))
                                                                               .setOrderGroup(orderGroupRepository.findById(orderDetailApiRequest.getOrderGroupId()).orElseThrow(NoSuchElementException::new)))
                                    .map(orderDetailRepository::save)
                                    .map(this::response)
                                    .orElseGet(() -> Header.ERROR("No Existed Data"));

    }

    @Override
    public Header delete(Long id) {
        return orderDetailRepository.findById(id)
                                    .map(orderDetail -> {
                                        orderDetailRepository.delete(orderDetail);
                                        return Header.OK();
                                    })
                                    .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
        OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
                                                                              .id(orderDetail.getId())
                                                                              .status(orderDetail.getStatus())
                                                                              .arrivalDate(orderDetail.getArrivalDate())
                                                                              .quantity(orderDetail.getQuantity())
                                                                              .totalPrice(orderDetail.getTotalPrice())
                                                                              .itemId(orderDetail.getItem().getId())
                                                                              .orderGroupId(orderDetail.getOrderGroup().getId())
                                                                              .build();
        return Header.OK(orderDetailApiResponse);
    }
}
