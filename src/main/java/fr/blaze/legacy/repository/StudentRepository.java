package fr.blaze.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.blaze.legacy.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
