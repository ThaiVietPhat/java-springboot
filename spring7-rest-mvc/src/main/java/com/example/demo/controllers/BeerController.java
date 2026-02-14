package com.example.demo.controllers;

import com.example.demo.model.BeerDTO;
import com.example.demo.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
	public static final String BEER_PATH = "/api/v1/beer";
	public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

	private final BeerService beerService;

	@PatchMapping(BEER_PATH_ID)
	public ResponseEntity updateBeerPatchById(@PathVariable UUID beerId, @RequestBody BeerDTO beer){
		beerService.beerPatchById(beerId,beer);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping(BEER_PATH_ID)
	public ResponseEntity deleteById(@PathVariable UUID beerId){
		beerService.deleteBeerById(beerId);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(BEER_PATH_ID)
	public ResponseEntity updateById(@PathVariable UUID beerId,@RequestBody BeerDTO beer){
		if(beerService.updateBeerById(beerId,beer).isEmpty()){
			throw new NotFoundException();
		}
		return ResponseEntity.noContent().build();
	}
	@PostMapping(BEER_PATH)
	public ResponseEntity handlePost(@RequestBody BeerDTO beer){
		BeerDTO savedBeer = beerService.saveNewBeer(beer);
		return ResponseEntity.created(URI.create(BEER_PATH + "/" + savedBeer.getId().toString())).build();
	}

	@GetMapping(BEER_PATH)
	public List<BeerDTO> listBeers() {
		log.debug("List Beers - in controller");
		return beerService.listBeers();
	}

	@GetMapping(BEER_PATH_ID)
	public BeerDTO getBeerById(@PathVariable UUID beerId) {
		log.debug("Get Beer by Id - in controller");
		return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
	}

}
