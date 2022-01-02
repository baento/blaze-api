package fr.blaze.model;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@Entity
public class Activity extends Resource {
    private boolean published;

    private Date startDate;
    private Date endDate;

    @ManyToOne
    private ActivityType type;

    public Duration getDuration() {
        return Duration.between(startDate.toInstant(), endDate.toInstant());
    }
}
