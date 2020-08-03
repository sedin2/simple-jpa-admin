package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.entitiy.Item;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.ItemApiRequest;
import com.shopping.simpleadmin.model.network.response.ItemApiResponse;
import com.shopping.simpleadmin.repository.ItemRepository;
import com.shopping.simpleadmin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ItemApiService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Item item = Item.builder()
                        .status(itemApiRequest.getStatus())
                        .name(itemApiRequest.getName())
                        .title(itemApiRequest.getTitle())
                        .content(itemApiRequest.getContent())
                        .price(itemApiRequest.getPrice())
                        .brandName(itemApiRequest.getBrandName())
                        .registeredAt(LocalDateTime.now())
                        .partner(partnerRepository.findById(itemApiRequest.getPartnerId())
                                                  .orElseThrow(NoSuchElementException::new))
                        .build();
        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header<ItemApiResponse> delete(Long id) {
        return null;
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
