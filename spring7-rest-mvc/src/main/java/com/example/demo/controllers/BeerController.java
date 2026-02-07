package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Beer;
import com.example.demo.services.BeerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
	public static final String BEER_PATH = "/api/v1/beer";
	public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

	private final BeerService beerService;

	@PatchMapping(BEER_PATH_ID)
	public ResponseEntity updateBeerPatchById(@PathVariable UUID beerId, @RequestBody Beer beer){
		beerService.beerPatchById(beerId,beer);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping(BEER_PATH_ID)
	public ResponseEntity deleteById(@PathVariable UUID beerId){
		beerService.deleteBeerById(beerId);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(BEER_PATH_ID)
	public ResponseEntity updateById(@PathVariable UUID beerId,@RequestBody Beer beer){
		beerService.updateBeerById(beerId,beer);
		return ResponseEntity.noContent().build();
	}
	@PostMapping(BEER_PATH)
	public ResponseEntity handlePost(@RequestBody Beer beer){
		Beer savedBeer = beerService.saveNewBeer(beer);
		return ResponseEntity.created(URI.create(BEER_PATH + "/" + savedBeer.getId().toString())).build();
	}

	@GetMapping(BEER_PATH)
	public List<Beer> listBeers() {
		log.debug("List Beers - in controller");
		return beerService.listBeers();
	}
	@GetMapping(BEER_PATH_ID)
	public Beer getBeerById(@PathVariable UUID beerId) {
		log.debug("Get Beer by Id - in controller");
		return beerService.getBeerById(beerId);
	}

}
