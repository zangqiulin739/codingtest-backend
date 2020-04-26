package com.itlize.codingtest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeypadConfiguration {
	@Bean
	public Map<Character, Character[]> keypadMap() {
		Map<Character, Character[]> map = new HashMap<>();
		
		map.put('0', new Character[] {'0'});
		map.put('1', new Character[] {'1'});
		map.put('2', new Character[] {'2', 'A', 'B', 'C'});
		map.put('3', new Character[] {'3', 'D', 'E', 'F'});
		map.put('4', new Character[] {'4', 'G', 'H', 'I'});
		map.put('5', new Character[] {'5', 'J', 'K', 'L'});
		map.put('6', new Character[] {'6', 'M', 'N', 'O'});
		map.put('7', new Character[] {'7', 'P', 'Q', 'R', 'S'});
		map.put('8', new Character[] {'8', 'T', 'U', 'V'});
		map.put('9', new Character[] {'9', 'W', 'X', 'Y', 'Z'});
		
		return map;
	}
}
