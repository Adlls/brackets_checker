package com.adls.beauty_brackets.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@Jacksonized
public class BracketsCheckerRq {
    @Size(max = 255, message = "Text's length cannot be more than 255")
    @NotBlank(message = "Text cannot be empty")
    public final String text;
}
