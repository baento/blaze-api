package fr.blaze.calendar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Getter;

@Getter
@Entity
public class User extends Resource {
    private String firstName;

    private String lastName;

    @ElementCollection
    @Column(name = "privilege")
    private Set<String> privileges = new HashSet<>();
}