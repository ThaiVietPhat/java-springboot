package com.example.demo.repositories;

import com.example.demo.bootstrap.BootstrapData;
import com.example.demo.entities.Beer;
import com.example.demo.models.BeerStyle;
import com.example.demo.services.BeerCsvServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
@ActiveProfiles("test")
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;
        @Test
        void testSaveBeer() {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("sdsdsdsdsd")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .build());
            beerRepository.flush();
            assertThat(savedBeer).isNotNull();
            assertThat(savedBeer.getId()).isNotNull();
        }


}