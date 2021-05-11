package com.kodilla.ecommercee.service;




import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final ProductsGroupDao groupRepository;


    public List<ProductsGroup> getAllGroups() {
        return (List<ProductsGroup>) groupRepository.findAll();
    }

    public ProductsGroup saveGroup(final ProductsGroup group) {
        return groupRepository.save(group);
    }

    public Optional<ProductsGroup> getGroup(final Long id) {
        return groupRepository.findById(id);
    }

}
