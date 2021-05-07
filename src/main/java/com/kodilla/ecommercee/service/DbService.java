package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.GroupDao;
import com.kodilla.ecommercee.domain.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final GroupDao groupDao;

    public List<Group> getAllGroups() {
        return groupDao.findAll();
    }

    public Group saveGroup(final Group group) {
        return groupDao.save(group);
    }

    public Optional<Group> getGroup(final Long id) {
        return groupDao.findById(id);
    }

    public void deleteGroup(final Long groupId) {
        groupDao.deleteById(groupId);
    }
}
