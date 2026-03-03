package com.example.demo.services;

import com.example.demo.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    List<BeerDTO> listBeers();
    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    boolean deleteBeerById(UUID beerId);

    Optional<BeerDTO> beerPatchById(UUID beerId, BeerDTO beer);
}