package fr.blaze.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.blaze.model.Personnel;
import fr.blaze.repository.PersonnelRepository;
import fr.blaze.service.PersonnelService;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public Personnel getPersonnel(int id) {
        return personnelRepository.findById(id).orElseThrow();
    }

    @Override
    public Collection<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();
    }
    
}
