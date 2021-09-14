package com.carre.exercise.exercise.dao;

import com.carre.exercise.exercise.entity.Nace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Daniel Carretero Ferres
 */

@Repository
public interface NaceDao extends JpaRepository<Nace, Long>
{

}
