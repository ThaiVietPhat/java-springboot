package com.example.demo.controllers;

import com.example.demo.config.SpringSecConfig;
import com.example.demo.models.BeerDTO;
import com.example.demo.services.BeerService;
import com.example.demo.services.BeerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(BeerController.class)
@ExtendWith(MockitoExtension.class)
@Import(SpringSecConfig.class)
class BeerControllerTest {
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockitoBean
	BeerService beerService;

	@Captor
	ArgumentCaptor<UUID> uuidArgumentCaptor;

	@Captor
	ArgumentCaptor<BeerDTO> beerArgumentCaptor;

	BeerServiceImpl beerServiceImpl;

	@BeforeEach
	void setUp() {
		// Khởi tạo impl để lấy data mẫu
		beerServiceImpl = new BeerServiceImpl();
	}

	@Test
	void testPatchBeer() throws Exception {
		// Khớp 4 tham số: (String, BeerStyle, Boolean, Pageable)
		BeerDTO beer = beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25))
				.getContent().get(0);

		Map<String, Object> beerMap = new HashMap<>();
		beerMap.put("beerName", "New Name");

		mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
						.with(httpBasic(USERNAME, PASSWORD))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beerMap)))
				.andExpect(status().isNoContent());

		verify(beerService).beerPatchById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());

		assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
		assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
	}



	@Test
	void testDeleteBeer() throws Exception {
		// Khớp 4 tham số
		BeerDTO beer = beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25))
				.getContent().get(0);

		given(beerService.deleteBeerById(any())).willReturn(true);

		mockMvc.perform(delete(BeerController.BEER_PATH_ID, beer.getId())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		verify(beerService).deleteBeerById(uuidArgumentCaptor.capture());

		assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
	}

	@Test
	void testUpdateBeer() throws Exception {
		// Khớp 4 tham số
		BeerDTO beer = beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25))
				.getContent().get(0);

		given(beerService.updateBeerById(any(), any())).willReturn(Optional.of(beer));

		mockMvc.perform(put(BeerController.BEER_PATH_ID, beer.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(beer))
		).andExpect(status().isNoContent());

		verify(beerService).updateBeerById(any(UUID.class), any(BeerDTO.class));
	}

	@Test
	void testCreateNewBeer() throws Exception {
		// SỬA: Thay 1, 25 thành PageRequest.of(0, 25) để khớp 4 tham số
		BeerDTO beer = beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25))
				.getContent().get(0);
		beer.setVersion(null);
		beer.setId(null);

		given(beerService.saveNewBeer(any(BeerDTO.class)))
				.willReturn(beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25)).getContent().get(1));

		mockMvc.perform(post(BeerController.BEER_PATH)
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beer))
				)
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}

	@Test
	void testCreateBeerNullBeerName() throws Exception {
		BeerDTO beerDTO = BeerDTO.builder().build();

		// SỬA: Thay 1, 25 thành PageRequest.of(0, 25)
		given(beerService.saveNewBeer(any(BeerDTO.class)))
				.willReturn(beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25)).getContent().get(1));

		mockMvc.perform(post(BeerController.BEER_PATH)
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beerDTO)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.length()", is(4)));
	}

	@Test
	void testListBeer() throws Exception {
		// Khớp 4 tham số: any() cho String, Style, Boolean và any(Pageable.class)
		given(beerService.pageBeers(any(), any(), any(), any(Pageable.class)))
				.willReturn(beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25)));

		mockMvc.perform(get(BeerController.BEER_PATH)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content.length()", is(3)));
	}

	@Test
	void testGetBeerById() throws Exception {
		// SỬA: Bỏ biến 'sor' bị lỗi syntax, thay bằng PageRequest chuẩn
		BeerDTO testBeer = beerServiceImpl.pageBeers(null, null, false, PageRequest.of(0, 25))
				.getContent().get(0);

		given(beerService.getBeerById(any(UUID.class)))
				.willReturn(Optional.of(testBeer));

		mockMvc.perform(get(BeerController.BEER_PATH_ID, testBeer.getId())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
				.andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));
	}

	@Test
	void testGetBeerByIdNotFound() throws Exception {
		given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());

		mockMvc.perform(get(BeerController.BEER_PATH_ID, UUID.randomUUID()))
				.andExpect(status().isNotFound());
	}
}