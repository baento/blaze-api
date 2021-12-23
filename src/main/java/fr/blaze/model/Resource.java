package fr.blaze.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Resource {
    @Id
    @GeneratedValue
    private final Integer id = 0;

    @NaturalId
    @NotNull
    @Column(unique = true, nullable = false)
    private String handle;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private LocalDateTime deletionDate;

    @JsonIgnore
    public boolean isDeleted() {
        return deletionDate != null && deletionDate.isBefore(LocalDateTime.now());
    }
}
