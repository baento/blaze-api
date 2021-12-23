package fr.blaze.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "`group`") // group is a reserved word in MySQL
public class Group extends Resource {

    @Builder.Default
    @ManyToMany(mappedBy = "groups")
    @JsonBackReference
    private Set<Student> students = new HashSet<>();
}
