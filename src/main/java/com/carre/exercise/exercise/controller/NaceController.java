package com.carre.exercise.exercise.controller;

import com.carre.exercise.exercise.dto.NaceDTO;
import com.carre.exercise.exercise.service.NaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author Daniel Carretero Ferres
 */

@RestController
public class NaceController
{
    @Autowired private NaceService naceService;

    // METHODS
    //--------------------------------------------------------------------------------------------------------
    @PostMapping("nace")
    public void putNaceDetails(@Valid @RequestBody NaceDTO naceDTO)
    {
        naceService.createNace(naceDTO);
    }

    @GetMapping("/nace/{id}")
    public NaceDTO getNaceDetails(@PathVariable Long id)
    {
        return naceService.getNaceDetails(id);
    }

    @GetMapping("/nace/csv")
    public void putNaceDetailsFromCSV() throws IOException
    {
        naceService.createNaceFromCSV();
        //chant parameter to accept uri with file path
    }
}
