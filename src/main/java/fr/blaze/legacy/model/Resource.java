package fr.blaze.legacy.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class Resource {
    @Id
    private int id;

    @NaturalId
    private String identifiant;

    private boolean deleted;

    private Date dateCreation;
    private Date dateModif;
}
