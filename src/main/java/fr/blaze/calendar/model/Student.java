package fr.blaze.calendar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Getter;

@Getter
@Entity
public class Student extends Resource {
    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(name = "student_groups", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private Set<Group> groups = new HashSet<>();
}
