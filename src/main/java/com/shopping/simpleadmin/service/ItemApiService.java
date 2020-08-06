package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.model.entitiy.Item;
import com.shopping.simpleadmin.model.enumclass.ItemStatus;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.ItemApiRequest;
import com.shopping.simpleadmin.model.network.response.ItemApiResponse;
import com.shopping.simpleadmin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ItemApiService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Item item = Item.builder()
                        .status(ItemStatus.REGISTERED)
                        .name(itemApiRequest.getName())
                        .title(itemApiRequest.getTitle())
                        .content(itemApiRequest.getContent())
                        .price(itemApiRequest.getPrice())
                        .brandName(itemApiRequest.getBrandName())
                        .registeredAt(LocalDateTime.now())
                        .partner(partnerRepository.findById(itemApiRequest.getPartnerId())
                                                  .orElseThrow(NoSuchElementException::new))
                        .build();
        Item newItem = baseRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                             .map(item -> response(item))
                             .orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    @Override
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        return baseRepository.findById(itemApiRequest.getId())
                             .map(entityItem -> entityItem.setStatus(itemApiRequest.getStatus())
                                                          .setName(itemApiRequest.getName())
                                                          .setTitle(itemApiRequest.getTitle())
                                                          .setContent(itemApiRequest.getContent())
                                                          .setPrice(itemApiRequest.getPrice())
                                                          .setBrandName(itemApiRequest.getBrandName())
                                                          .setRegisteredAt(itemApiRequest.getRegisteredAt())
                                                          .setUnregisteredAt(itemApiRequest.getUnregisteredAt()))
                             .map(newEntityItem -> baseRepository.save(newEntityItem))
                             .map(item -> response(item))
                             .orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id).map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("Not Existed Data"));
    }

    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                                                         .id(item.getId())
                                                         .status(item.getStatus())
                                                         .name(item.getName())
                                                         .title(item.getTitle())
                                                         .content(item.getContent())
                                                         .price(item.getPrice())
                                                         .brandName(item.getBrandName())
                                                         .registeredAt(item.getRegisteredAt())
                                                         .unregisteredAt(item.getUnregisteredAt())
                                                         .partnerId(item.getPartner().getId())
                                                         .build();
        return Header.OK(itemApiResponse);
    }
}
