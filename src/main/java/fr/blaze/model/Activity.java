package fr.blaze.model;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
public class Activity extends Resource {
    private boolean published;

    private Date startDate;
    private Date endDate;

    @OneToOne
    private ActivityType type;

    public Duration getDuration() {
        return Duration.between(startDate.toInstant(), endDate.toInstant());
    }
}
