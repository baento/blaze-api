package fr.blaze.service;

import java.util.Collection;

import fr.blaze.model.Personnel;

public interface PersonnelService {
    public Personnel getPersonnel(int id);

    public Collection<Personnel> getAllPersonnels();
}
