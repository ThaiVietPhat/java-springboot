package com.example.demo.services;
import java.util.List;
import java.util.UUID;
import com.example.demo.model.Beer;

public interface BeerService {
    List<Beer> listBeers();
    Beer getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteBeerById(UUID beerId);

    void beerPatchById(UUID beerId, Beer beer);
}