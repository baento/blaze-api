package fr.blaze.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "`group`") // group is a reserved word in MySQL
public class Group extends Resource {

    @Builder.Default
    @ManyToMany(mappedBy = "groups")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();
}
