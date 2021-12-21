package fr.blaze.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.blaze.calendar.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
