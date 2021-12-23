package fr.blaze.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StudentPayload {
    @NotBlank
    private String handle;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
