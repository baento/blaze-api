package fr.blaze.calendar.model;

import javax.persistence.Entity;

import lombok.Getter;

@Getter
@Entity
public class ActivityType extends Resource {
    private String comment;
}
