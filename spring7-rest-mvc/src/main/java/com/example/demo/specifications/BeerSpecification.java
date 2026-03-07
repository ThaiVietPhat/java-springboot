package com.example.demo.specifications;

import com.example.demo.entities.Beer;
import com.example.demo.models.BeerStyle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BeerSpecification {
    public static Specification<Beer> hasName(String beerName){
        return (root, query, cb) -> {
            if(!StringUtils.hasText(beerName)) return null;
            return cb.like(cb.lower(root.get("beerName")), "%" + beerName.toLowerCase() + "%");
        };
    }
    public static Specification<Beer> hasStyle(BeerStyle beerStyle) {
        return (root, query, cb) -> {
            if (beerStyle == null) return null;
            return cb.equal(root.get("beerStyle"), beerStyle);
        };
    }
}
