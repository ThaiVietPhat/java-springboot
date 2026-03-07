package com.example.demo.services;

import com.example.demo.models.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
class BeerCsvServiceImplTest {
    BeerCsvServiceImpl beerCsvService = new BeerCsvServiceImpl();

    @Test
    void revertCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/Beers.csv");
        List<BeerCSVRecord> beerCSVRecords = beerCsvService.convertCSV(file);
        System.out.println(beerCSVRecords.size());
        assertThat(beerCSVRecords.size()).isGreaterThan(0);
    }
}