package com.example.demo.controllers;

import com.example.demo.entities.Beer;
import com.example.demo.mappers.BeerMapper;
import com.example.demo.model.BeerDTO;
import com.example.demo.repositories.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    private BeerMapper beerMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    void testPatchBeerNoName() throws Exception {
        Beer beer= beerRepository.findAll().get(0);
        String oldName = beer.getBeerName();
        Map<String, Object> beerMap= new HashMap<>();
        // Không đưa beerName vào map, hoặc đưa vào null
        beerMap.put("price", 12.99);

        mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest()); // Kỳ vọng 400 vì @Valid đã bật
    }

    @Test
    void testPatchBeerBadName() throws Exception {
        Beer beer= beerRepository.findAll().get(0);
        Map<String, Object> beerMap= new HashMap<>();
        beerMap.put("beerName", "New com.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeercom.example.demo.controllers.BeerControllerIT#testPatchBeerNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsdsNamesdsdsdsds");
        MvcResult result = mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()").value(4))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }
    @Test
    void testUpdateNoteFound(){
        assertThrows(NotFoundException.class, () -> beerController.updateById(UUID.randomUUID(),BeerDTO.builder().build()));
    }
    @Test
    void testListBeers(){
        List<BeerDTO> dtos = beerController.listBeers();
        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();
        assertThat(dtos.size()).isEqualTo(0);
    }
    @Test
    void testGetById(){
        Beer beer= beerRepository.findAll().get(0);
        BeerDTO dtos = beerController.getBeerById(beer.getId());
        assertThat(dtos).isNotNull();
    }
    @Test
    void testBeerIdNotFound(){
        assertThrows(NotFoundException.class, () ->{
            beerController.getBeerById(UUID.randomUUID());
                });
    }
    @Rollback
    @Transactional
    @Test
    void testNewBeer(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();

        ResponseEntity responseEntity = beerController.handlePost(beerDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        String[] locationUUID = responseEntity.getHeaders().getLocation().toString().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }
    @Rollback
    @Transactional
    @Test
    void testUpdateBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED BEER";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(),beerDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);

    }
    @Rollback
    @Transactional
    @Test
    void testDeleteByIdFound(){
        Beer beer = beerRepository.findAll().get(0);
        ResponseEntity responseEntity = beerController.deleteById(beer.getId());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }
    @Test
    void testDeleteByIdNotFound(){
        assertThrows(NotFoundException.class, () -> beerController.deleteById(UUID.randomUUID()));
    }

}