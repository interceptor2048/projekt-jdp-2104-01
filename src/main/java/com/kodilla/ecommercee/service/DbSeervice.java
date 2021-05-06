package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.GroupRepository;
import com.kodilla.ecommercee.domain.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbSeervice {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroup(final Long id) {
        return groupRepository.findById(id);
    }

    public void donothing() {}
}
