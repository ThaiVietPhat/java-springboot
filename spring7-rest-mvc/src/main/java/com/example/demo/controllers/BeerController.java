package com.example.demo.controllers;

import com.example.demo.models.BeerDTO;
import com.example.demo.models.BeerStyle;
import com.example.demo.services.BeerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
	public static final String BEER_PATH = "/api/v1/beer";
	public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

	private final BeerService beerService;

	@PatchMapping(BEER_PATH_ID)
	public ResponseEntity updateBeerPatchById( @PathVariable UUID beerId,  @RequestBody BeerDTO beer){
		beerService.beerPatchById(beerId,beer);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping(BEER_PATH_ID)
	public ResponseEntity deleteById(@PathVariable UUID beerId){
		if (! beerService.deleteBeerById(beerId)){
			throw new NotFoundException();
		}

		return ResponseEntity.noContent().build();
	}
	@PutMapping(BEER_PATH_ID)
	public ResponseEntity updateById( @PathVariable UUID beerId, @Valid @RequestBody BeerDTO beer){
		beerService.updateBeerById(beerId,beer).orElseThrow(NotFoundException::new);
		return ResponseEntity.noContent().build();
	}
	@PostMapping(BEER_PATH)
	public ResponseEntity handlePost(@Valid @RequestBody BeerDTO beer){
		BeerDTO savedBeer = beerService.saveNewBeer(beer);
		return ResponseEntity.created(URI.create(BEER_PATH + "/" + savedBeer.getId().toString())).build();
	}

	@GetMapping(BEER_PATH)
	public Page<BeerDTO> pageBeers(@RequestParam(required = false) String beerName,
                                   @RequestParam(required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false, defaultValue = "false") Boolean showInventory,
								   @PageableDefault(page = 0, size = 25, sort = "beerName") Pageable pageable){
		return beerService.pageBeers(beerName, beerStyle, showInventory, pageable);
	}

	@GetMapping(BEER_PATH_ID)
	public BeerDTO getBeerById(@PathVariable UUID beerId) {
		log.debug("Get Beer by Id - in controller");
		return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
	}

}
