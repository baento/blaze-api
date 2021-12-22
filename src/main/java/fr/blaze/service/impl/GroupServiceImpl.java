package fr.blaze.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.blaze.model.Group;
import fr.blaze.repository.GroupRepository;
import fr.blaze.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Collection<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    
}
