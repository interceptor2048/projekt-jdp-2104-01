package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.GroupRepository;
import com.kodilla.ecommercee.domain.ProductsGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbSeervice {

    private final GroupRepository groupRepository;

    public List<ProductsGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    public ProductsGroup saveGroup(final ProductsGroup productsGroup) {
        return groupRepository.save(productsGroup);
    }

    public Optional<ProductsGroup> getGroup(final Long id) {
        return groupRepository.findById(id);
    }

    public void donothing() {}
}
