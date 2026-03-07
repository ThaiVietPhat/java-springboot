package com.example.demo.mappers;

import com.example.demo.entities.Beer;
import com.example.demo.models.BeerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface BeerMapper {
    Beer beerDTOToBeer(BeerDTO dto);
    BeerDTO beerToBeerDTO(Beer beer);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateBeerFromDto(BeerDTO dto, @MappingTarget Beer entity);

}
