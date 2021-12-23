package fr.blaze.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
@Entity
public class User extends Resource {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
