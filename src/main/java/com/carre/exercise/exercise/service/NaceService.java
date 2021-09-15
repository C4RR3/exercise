package com.carre.exercise.exercise.service;

import com.carre.exercise.exercise.dao.NaceDao;
import com.carre.exercise.exercise.dto.NaceDTO;
import com.carre.exercise.exercise.dto.NaceMapper;
import com.carre.exercise.exercise.entity.Nace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Daniel Carretero Ferres
 */

@Service @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
public class NaceService
{
    @Autowired private final NaceDao naceDao;
    @Autowired private final NaceMapper naceMapper;

    // METHODS
    //--------------------------------------------------------------------------------------------------------
    @Transactional
    public void createNace(NaceDTO naceDTO)
    {
        Nace nace = naceMapper.dtoToEntity(naceDTO);
        nace.setNew(true);
        naceDao.save(nace);
    }

    @Transactional
    public NaceDTO getNaceDetails(Long id)
    {
        Optional<Nace> nace = naceDao.findById(id);

        return nace
            .map(naceMapper::entityToDTO)
            .orElseThrow(() -> new IllegalArgumentException("Order Not Found: " + id));
    }
}
