package com.carre.exercise.exercise.dto;

import lombok.*;

/**
 * @author Daniel Carretero Ferres
 */

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class NaceDTO
{
    private Long orderId;
    private int orderLevel;
    private String code;
    private String parent;
    private String description;
    private String includeOne;
    private String includeTwo;
    private String rulings;
    private String exclude;
    private String referenceIsicV4;
}
