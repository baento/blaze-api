package fr.blaze.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.apache.commons.collections4.SetUtils;

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
public class Calendar extends Resource {
    @Builder.Default
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"))
    private Set<Activity> activities = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private Set<Group> groups = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> students = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> students_exceptions = new HashSet<>();

    public Set<Student> getStudents() {
        return SetUtils.difference(students, students_exceptions);
    }
}
