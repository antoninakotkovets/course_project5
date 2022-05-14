package com.ajuncodes.coursework.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ComparisonResultDto {

    private Double result;
    private Set<String> similarities;
}
