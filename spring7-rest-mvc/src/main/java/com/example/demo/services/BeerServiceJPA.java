package com.example.demo.services;

import com.example.demo.entities.Beer;
import com.example.demo.mappers.BeerMapper;
import com.example.demo.models.BeerDTO;
import com.example.demo.models.BeerStyle;
import com.example.demo.repositories.BeerRepository;
import com.example.demo.specifications.BeerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService{
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final int DEFAULT_PAGE_SIZE = 25;
    private final int DEFAULT_PAGE = 0;
    @Override
    public Page<BeerDTO> pageBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Pageable pageable) {
        Specification<Beer> spec = Specification.where(BeerSpecification.hasName(beerName))
                .and(BeerSpecification.hasStyle(beerStyle));
        Page<Beer> beerPage = beerRepository.findAll(spec, pageable);

        return beerPage.map(beer -> {
            BeerDTO dto = beerMapper.beerToBeerDTO(beer);

            if (Boolean.FALSE.equals(showInventory)) {
                dto.setQuantityOnHand(null);
            }

            return dto;
        });
    }
    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return beerRepository.findById(id)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        return beerRepository.findById(beerId).map(foundBeer -> {
            beerMapper.updateBeerFromDto(beer, foundBeer);
            return beerMapper.beerToBeerDTO(beerRepository.save(foundBeer));
        });

    }

    @Override
    public Optional<BeerDTO> beerPatchById(UUID beerId, BeerDTO beer) {
        return beerRepository.findById(beerId).map(foundBeer -> {
            if(StringUtils.hasText(beer.getBeerName())){
                foundBeer.setBeerName(beer.getBeerName());
            }
            if(beer.getBeerStyle() != null){
                foundBeer.setBeerStyle(beer.getBeerStyle());
            }
            if(beer.getPrice() != null){
                foundBeer.setPrice(beer.getPrice());
            }
            if(StringUtils.hasText(beer.getUpc())){
                foundBeer.setUpc(beer.getUpc());
            }
            if (beer.getQuantityOnHand() != null){
                foundBeer.setQuantityOnHand(beer.getQuantityOnHand());
            }
            return beerMapper.beerToBeerDTO(beerRepository.save(foundBeer));
        });
    }

    @Override
    public boolean deleteBeerById(UUID beerId) {
        if (beerRepository.existsById(beerId)) {
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }
}
