package fr.blaze.model;

import javax.persistence.Entity;

import lombok.Getter;

@Getter
@Entity
public class ActivityType extends Resource {
    private String comment;
}
