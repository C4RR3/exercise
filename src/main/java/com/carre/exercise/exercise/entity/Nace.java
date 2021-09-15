package com.carre.exercise.exercise.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

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
    @Size(max = 8000)
    private String parent;
    @Size(max = 8000)
    private String description;
    @Size(max = 8000)
    private String includeOne;
    @Size(max = 8000)
    private String includeTwo;
    @Size(max = 8000)
    private String rulings;
    @Size(max = 8000)
    private String exclude;
    @Size(max = 8000)
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
