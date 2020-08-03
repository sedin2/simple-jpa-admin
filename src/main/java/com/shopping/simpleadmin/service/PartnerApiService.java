package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.entitiy.Partner;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.PartnerApiRequest;
import com.shopping.simpleadmin.model.network.response.PartnerApiResponse;
import com.shopping.simpleadmin.repository.CategoryRepository;
import com.shopping.simpleadmin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class PartnerApiService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                                 .name(partnerApiRequest.getName())
                                 .status(partnerApiRequest.getStatus())
                                 .address(partnerApiRequest.getAddress())
                                 .callCenter(partnerApiRequest.getCallCenter())
                                 .partnerNumber(partnerApiRequest.getPartnerNumber())
                                 .businessNumber(partnerApiRequest.getBusinessNumber())
                                 .ceoName(partnerApiRequest.getCeoName())
                                 .registeredAt(LocalDateTime.now())
                                 .category(categoryRepository.findById(partnerApiRequest.getCategoryId()).orElseThrow(NoSuchElementException::new))
                                 .build();
        Partner newPartner = partnerRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return partnerRepository.findById(id)
                                .map(this::response)
                                .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        return partnerRepository.findById(partnerApiRequest.getId())
                                .map(entityPartner -> entityPartner.setName(partnerApiRequest.getName())
                                                                   .setStatus(partnerApiRequest.getStatus())
                                                                   .setAddress(partnerApiRequest.getAddress())
                                                                   .setCallCenter(partnerApiRequest.getCallCenter())
                                                                   .setPartnerNumber(partnerApiRequest.getPartnerNumber())
                                                                   .setBusinessNumber(partnerApiRequest.getBusinessNumber())
                                                                   .setCeoName(partnerApiRequest.getCeoName())
                                                                   .setRegisteredAt(LocalDateTime.now())
                                                                   .setUnregisteredAt(partnerApiRequest.getUnregisteredAt())
                                                                   .setCategory(categoryRepository.findById(partnerApiRequest.getCategoryId()).orElseThrow(NoSuchElementException::new)))
                                .map(newPartner -> partnerRepository.save(newPartner))
                                .map(this::response)
                                .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return partnerRepository.findById(id)
                                .map(partner -> {
                                    partnerRepository.delete(partner);
                                    return Header.OK();
                                })
                                .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    private Header<PartnerApiResponse> response(Partner partner) {
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                                                                  .id(partner.getId())
                                                                  .name(partner.getName())
                                                                  .status(partner.getStatus())
                                                                  .address(partner.getAddress())
                                                                  .callCenter(partner.getCallCenter())
                                                                  .partnerNumber(partner.getPartnerNumber())
                                                                  .businessNumber(partner.getBusinessNumber())
                                                                  .ceoName(partner.getCeoName())
                                                                  .registeredAt(partner.getRegisteredAt())
                                                                  .unregisteredAt(partner.getUnregisteredAt())
                                                                  .categoryId(partner.getCategory().getId())
                                                                  .build();
        return Header.OK(partnerApiResponse);
    }
}
