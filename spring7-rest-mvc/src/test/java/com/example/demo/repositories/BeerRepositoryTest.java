package com.example.demo.repositories;

import com.example.demo.entities.Beer;
import com.example.demo.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;
        @Test
        void testSaveBeer(){
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName(" s")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .build());
            beerRepository.flush();
            assertThat(savedBeer).isNotNull();
            assertThat(savedBeer.getId()).isNotNull();
        }

}