package fr.blaze.model;

import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@Entity
public class Course extends Resource {
    private String title;

    private Duration totalDuration;

    private Duration sessionDuration;

    private int weeklyCount;

    @ManyToOne
    @JsonBackReference
    private ActivityType activityType;

    @ManyToOne
    @JsonBackReference
    private Component component;
}
