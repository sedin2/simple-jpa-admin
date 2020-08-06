package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.model.entitiy.Partner;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.PartnerApiRequest;
import com.shopping.simpleadmin.model.network.response.PartnerApiResponse;
import com.shopping.simpleadmin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class PartnerApiService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

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
        Partner newPartner = baseRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                             .map(this::response)
                             .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        return baseRepository.findById(partnerApiRequest.getId())
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
                             .map(newPartner -> baseRepository.save(newPartner))
                             .map(this::response)
                             .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                             .map(partner -> {
                                 baseRepository.delete(partner);
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
