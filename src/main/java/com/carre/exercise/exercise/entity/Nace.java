package com.carre.exercise.exercise.entity;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Daniel Carretero Ferres
 */

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@ToString(onlyExplicitlyIncluded = true) @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Nace
{
    @Id
    @NotNull @EqualsAndHashCode.Include @ToString.Include
    private Long orderId;

    private int orderLevel;
    private String code;
    private String parent;
    private String description;
    private String inclideOne;
    private String includeTwo;
    private String rulings;
    private String exclude;
    private String referenceIsicV4;
}
