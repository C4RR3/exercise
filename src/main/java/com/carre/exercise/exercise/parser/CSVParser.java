package com.carre.exercise.exercise.parser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Daniel Carretero Ferres
 */
@Component @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CSVParser
{
    public <T> List<T> fromCSVtoJSON(String fileName, Class<T> type) throws IOException
    {
        File input = new File(fileName);

        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        MappingIterator<T> values = csvMapper.readerFor(type).with(csvSchema).readValues(input);
        return values.readAll();
    }
}
