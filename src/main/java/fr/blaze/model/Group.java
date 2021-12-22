package fr.blaze.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;

@Getter
@Entity
@Table(name = "`group`") // group is a reserved word in MySQL
public class Group extends Resource {

    @ManyToMany(mappedBy = "groups")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();
}
