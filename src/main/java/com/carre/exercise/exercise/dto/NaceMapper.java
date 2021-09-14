package com.carre.exercise.exercise.dto;

import com.carre.exercise.exercise.entity.Nace;
import org.mapstruct.Mapper;

/**
 * @author Daniel Carretero Ferres
 */

@Mapper(componentModel = "spring")
public interface NaceMapper
{
    Nace dtoToEntity(NaceDTO naceDTO);

    NaceDTO entityToDTO(Nace nace);
}
