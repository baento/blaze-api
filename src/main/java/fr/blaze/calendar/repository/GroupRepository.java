package fr.blaze.calendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.blaze.calendar.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findById(Integer id);

    Optional<Group> findByHandle(String handle);
}
