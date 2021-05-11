package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.domain.ProductsGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsGroupService {

    private final ProductsGroupDao groupRepository;

    public List<ProductsGroup> findAll() {
        return groupRepository.findAll();
    }

    public ProductsGroup save(final ProductsGroup group) {
        return groupRepository.save(group);
    }

    public Optional<ProductsGroup> findById(final Long id) {
        return groupRepository.findById(id);
    }

    public void deleteById(final Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
