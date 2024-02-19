package com.example.securityproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String comment;

    @NotNull
    private Integer likes;

}
