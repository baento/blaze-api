package fr.blaze.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.blaze.model.Group;
import fr.blaze.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public Collection<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
}
