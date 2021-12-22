package fr.blaze.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.blaze.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(Integer id);

    Optional<Student> findByHandle(String handle);
}