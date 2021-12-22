package fr.blaze.service;

import java.util.Collection;

import fr.blaze.model.Student;

public interface StudentService {
    public Student getStudent(int id);

    public Collection<Student> getAllStudents();
}
