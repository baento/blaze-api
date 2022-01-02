package fr.blaze.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class Resource {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @NotNull
    @Column(unique = true, nullable = false)
    private String handle;

    private String comment;

    @ManyToOne
    private Personnel owner;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private LocalDateTime deletionDate;

    @JsonIgnore
    public boolean isActive() {
        return deletionDate == null || deletionDate.isAfter(LocalDateTime.now());
    }
}
