package fr.blaze.service;

import java.util.Collection;

import fr.blaze.model.Group;

public interface GroupService {
    public Group getGroup(int id);

    public Collection<Group> getAllGroups();
}
