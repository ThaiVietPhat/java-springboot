package com.example.demo.services;

import com.example.demo.models.BeerDTO;
import com.example.demo.models.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Page<BeerDTO> pageBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Pageable pageable);
    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    boolean deleteBeerById(UUID beerId);

    Optional<BeerDTO> beerPatchById(UUID beerId, BeerDTO beer);
}