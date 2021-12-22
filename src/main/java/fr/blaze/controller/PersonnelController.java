package fr.blaze.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.blaze.model.Personnel;
import fr.blaze.service.PersonnelService;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping
    public Collection<Personnel> getAllPersonnels() {
        return personnelService.getAllPersonnels();
    }
}
