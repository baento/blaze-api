package fr.blaze.model;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
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
