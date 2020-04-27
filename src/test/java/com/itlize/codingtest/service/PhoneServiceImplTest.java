package com.itlize.codingtest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PhoneServiceImplTest {
	
	@Autowired
	private PhoneService phoneService;
	
	@Test
	public void whenGetTotalPhoneCount_thenReturnInt() {
		assertThat(phoneService.getTotalPhoneCount(null)).isEqualTo(0);
		assertThat(phoneService.getTotalPhoneCount("")).isEqualTo(0);
		assertThat(phoneService.getTotalPhoneCount("0")).isEqualTo(1);
		assertThat(phoneService.getTotalPhoneCount("0000011")).isEqualTo(1);
		assertThat(phoneService.getTotalPhoneCount("0000005678")).isEqualTo(4 * 4 * 5 * 4);
	}
	
	@Test
	public void whenGetItems_thenReturnList() {
		assertThat(phoneService.getItems(null, 0, 0)).isEmpty();
		assertThat(phoneService.getItems("123", 0, 0)).isEmpty();
		assertThat(phoneService.getItems("123", 50, 5)).isEmpty();
		assertThat(phoneService.getItems("123456", 4, 0))
		.hasSize(4)
		.startsWith("123456")
		.endsWith("12345O");
		assertThat(phoneService.getItems("123456", 4, 1))
		.hasSize(4)
		.startsWith("1234J6")
		.endsWith("1234JO");
	}
}
