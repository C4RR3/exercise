package com.carre.exercise.exercise.service;

import com.carre.exercise.exercise.dao.NaceDao;
import com.carre.exercise.exercise.dto.NaceDTO;
import com.carre.exercise.exercise.dto.NaceMapper;
import com.carre.exercise.exercise.entity.Nace;
import com.carre.exercise.exercise.parser.CSVParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Daniel Carretero Ferres
 */

@Service @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
public class NaceService
{
    @Autowired private final NaceDao naceDao;
    @Autowired private final NaceMapper naceMapper;
    @Autowired private final CSVParser csvParser;

    private static final String CSV_FILE_NAME = "NACE_REV2_20210915_154937.csv";
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
    public void createNaces(Collection<NaceDTO> collection)
    {
        List<Nace> entityList = collection.stream()
            .map(naceMapper::dtoToEntity).collect(Collectors.toList());

        entityList.forEach(entity -> entity.setNew(true));

        naceDao.saveAll(entityList);
    }

    @Transactional
    public NaceDTO getNaceDetails(Long id)
    {
        Optional<Nace> nace = naceDao.findById(id);

        return nace
            .map(naceMapper::entityToDTO)
            .orElseThrow(() -> new IllegalArgumentException("Order Not Found: " + id));
    }

    @Transactional
    public void createNaceFromCSV() throws IOException
    {
        List<NaceDTO> dtoList = csvParser.fromCSVtoJSON(CSV_FILE_NAME, NaceDTO.class);

        createNaces(dtoList);
    }
}
