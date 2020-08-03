package com.shopping.simpleadmin.service;

import com.shopping.simpleadmin.ifs.CrudInterface;
import com.shopping.simpleadmin.model.entitiy.Category;
import com.shopping.simpleadmin.model.network.Header;
import com.shopping.simpleadmin.model.network.request.CategoryApiRequest;
import com.shopping.simpleadmin.model.network.response.CategoryApiResponse;
import com.shopping.simpleadmin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryApiService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        Category category = Category.builder()
                                    .type(categoryApiRequest.getType())
                                    .title(categoryApiRequest.getTitle())
                                    .build();
        Category newCategory = categoryRepository.save(category);
        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return categoryRepository.findById(id)
                                 .map(this::response)
                                 .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        return categoryRepository.findById(categoryApiRequest.getId())
                                 .map(entityCategory -> entityCategory.setType(categoryApiRequest.getType())
                                                                      .setTitle(categoryApiRequest.getTitle()))
                                 .map(categoryRepository::save)
                                 .map(this::response)
                                 .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    @Override
    public Header delete(Long id) {
        return categoryRepository.findById(id)
                                 .map(category -> {
                                     categoryRepository.delete(category);
                                     return Header.OK();
                                 })
                                 .orElseGet(() -> Header.ERROR("No Existed Data"));
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                                                                     .id(category.getId())
                                                                     .type(category.getType())
                                                                     .title(category.getTitle())
                                                                     .build();
        return Header.OK(categoryApiResponse);
    }
}
