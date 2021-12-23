package fr.blaze.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.blaze.model.Group;
import fr.blaze.model.Student;
import fr.blaze.service.GroupService;
import fr.blaze.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestParam("handle") String handle, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Student student = Student.builder()
                .handle(handle)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return studentService.saveStudent(student);
    }

    @GetMapping("/{userid}")
    public Student getStudent(int userid) {
        return studentService.getStudent(userid);
    }

    @GetMapping("/{userid}/groups")
    public Collection<Group> getGroups(int userid) {
        return studentService.getStudent(userid).getGroups();
    }

    @PostMapping("/{userId}/groups")
    public void addGroup(int userId, int groupId) {
        Student student = studentService.getStudent(userId);
        Group group = groupService.getGroup(groupId);

        student.getGroups().add(group);
        studentService.saveStudent(student);
    }

    @DeleteMapping("/{userId}/groups/{groupId}")
    public void removeGroup(int userId, int groupId) {
        Student student = studentService.getStudent(userId);
        Group group = groupService.getGroup(groupId);

        student.getGroups().remove(group);
        studentService.saveStudent(student);
    }
}
