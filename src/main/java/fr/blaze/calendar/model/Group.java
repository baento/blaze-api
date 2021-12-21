package fr.blaze.calendar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "`group`") // group is a reserved word in MySQL
public class Group extends Resource {

    @ManyToMany(mappedBy = "groups")
    private Set<Student> students = new HashSet<>();
}
