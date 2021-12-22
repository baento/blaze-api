package fr.blaze.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.blaze.model.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    Optional<Personnel> findById(Integer id);

    Optional<Personnel> findByHandle(String handle);
}