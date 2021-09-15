package com.carre.exercise.exercise.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Daniel Carretero Ferres
 */

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@ToString(onlyExplicitlyIncluded = true) @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Nace implements Persistable<Long>
{
    @Id
    @NotNull @EqualsAndHashCode.Include @ToString.Include
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

    // Persistable
    //--------------------------------------------------------------------------------------------------------
    @Transient
    private boolean isNew = false;

    @Override
    public Long getId()
    {
        return orderId;
    }
}
