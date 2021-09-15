package com.carre.exercise.exercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Daniel Carretero Ferres
 */

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class NaceDTO
{
    @JsonProperty("Order")
    private Long orderId;
    @JsonProperty("Level")
    private int orderLevel;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Parent")
    private String parent;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("This item includes")
    private String includeOne;
    @JsonProperty("This item also includes")
    private String includeTwo;
    @JsonProperty("Rulings")
    private String rulings;
    @JsonProperty("This item excludes")
    private String exclude;
    @JsonProperty("Reference to ISIC Rev. 4")
    private String referenceIsicV4;
}
