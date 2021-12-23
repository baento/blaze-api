package fr.blaze.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.blaze.model.Group;
import fr.blaze.model.Student;
import fr.blaze.payload.StudentPayload;
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

    @GetMapping("/{userId}")
    public Student getStudent(@PathVariable Integer userId) {
        return studentService.getStudent(userId);
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentPayload studentPayload) {
        Student student = Student.builder()
                .handle(studentPayload.getHandle())
                .firstName(studentPayload.getFirstName())
                .lastName(studentPayload.getLastName())
                .build();

        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{userId}")
    public void deleteStudent(@PathVariable Integer userId) {

    }

    @GetMapping("/{userId}/groups")
    public Collection<Group> getGroups(Integer userid) {
        return studentService.getStudent(userid).getGroups();
    }

    @PutMapping("/{userId}/groups")
    public void addGroup(@PathVariable Integer userId, @PathVariable Integer groupId) {
        Student student = studentService.getStudent(userId);
        Group group = groupService.getGroup(groupId);

        student.getGroups().add(group);
        group.getStudents().add(student);

        studentService.saveStudent(student);
        groupService.saveGroup(group);
    }

    @DeleteMapping("/{userId}/groups/{groupId}")
    public void removeGroup(@PathVariable int userId, @PathVariable int groupId) {
        Student student = studentService.getStudent(userId);
        Group group = groupService.getGroup(groupId);

        student.getGroups().remove(group);
        group.getStudents().remove(student);

        studentService.saveStudent(student);
        groupService.saveGroup(group);
    }
}
