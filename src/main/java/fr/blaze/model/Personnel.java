package fr.blaze.model;

import javax.persistence.Entity;

import lombok.Getter;

@Getter
@Entity
public class Personnel extends Resource {
    private String firstName;

    private String lastName;
}