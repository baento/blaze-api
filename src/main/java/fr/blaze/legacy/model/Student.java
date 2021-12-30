package fr.blaze.legacy.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

@Entity
@AttributeOverride(name = "id", column = @Column(name = "codeEtudiant"))
public class Student extends Resource {

    private String email;

    private String nom;

    private String prenom;

    private Date dateNaissance;
}
