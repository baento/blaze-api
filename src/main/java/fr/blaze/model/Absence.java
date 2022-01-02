package fr.blaze.model;

import java.util.Date;

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
public class Absence extends Resource {

    @ManyToOne
    private AbsenceReason reason;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JsonBackReference
    private User user;
}
