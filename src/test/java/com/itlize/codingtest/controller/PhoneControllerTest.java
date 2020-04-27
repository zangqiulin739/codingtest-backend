package com.itlize.codingtest.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.itlize.codingtest.service.PhoneService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PhoneController.class)
class PhoneControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PhoneService phoneService;
	
	@Test
	void whenValidInput_thenReturnCount() throws Exception {
				
		Mockito.when(phoneService.getTotalPhoneCount("0000001111")).thenReturn(1);
		Mockito.when(phoneService.getTotalPhoneCount("0001111")).thenReturn(1);

		mvc.perform(get("/phone/count")
				.contentType("application/json")
				.param("number", "0000001111"))
		.andExpect(status().isOk())
		.andExpect(content().string("1"));
		
		mvc.perform(get("/phone/count")
				.contentType("application/json")
				.param("number", "+ (000) 000 1111"))
		.andExpect(status().isOk())
		.andExpect(content().string("1"));
		
		mvc.perform(get("/phone/count")
				.contentType("application/json")
				.param("number", "000 1111"))
		.andExpect(status().isOk())
		.andExpect(content().string("1"));
	}
	
	@Test
	public void whenInvalidInput_thenReturn400() throws Exception {
		mvc.perform(get("/phone/count")
				.contentType("application/json")
				.param("number", "(000) 000 111"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenValidInput_thenReturnItems() throws Exception {
		
		List<String> expectedRes = new ArrayList<>();
		expectedRes.add("0000001111");
		
		Mockito.when(phoneService.getItems("0000001111", 25, 0))
		.thenReturn(expectedRes);

		mvc.perform(get("/phone/items")
				.contentType("application/json")
				.param("number", "+(000) 000 1111"))
		.andExpect(content().string("[\"0000001111\"]"));
	}
}
