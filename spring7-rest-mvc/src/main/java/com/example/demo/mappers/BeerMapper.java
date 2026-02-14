package com.example.demo.mappers;

import com.example.demo.entities.Beer;
import com.example.demo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDTOToBeer(BeerDTO dto);
    BeerDTO beerToBeerDTO(Beer beer);
}
