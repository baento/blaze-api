package fr.blaze.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.apache.commons.collections4.SetUtils;

@Entity
public class Calendar extends Resource {
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"))
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private Set<Group> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> students_exceptions = new HashSet<>();

    public Set<Student> getStudents() {
        return SetUtils.difference(students, students_exceptions);
    }
}
