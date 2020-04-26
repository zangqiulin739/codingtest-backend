package com.itlize.codingtest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itlize.codingtest.domain.PhoneNumber;
import com.itlize.codingtest.service.PhoneService;

import lombok.Builder.Default;


@RestController
@CrossOrigin
@RequestMapping("phone")
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
			
	@GetMapping("count")
	public int getCount(@Valid PhoneNumber number) {
		return phoneService.getTotalPhoneCount(number.format());
	}
	
	@GetMapping("items")
	public List<String> getItems( PhoneNumber number, 
			@RequestParam(defaultValue="25") int pageSize, 
			@RequestParam(defaultValue="0") int pageIndex) {
		return phoneService.getItems(number.format(),  pageSize,  pageIndex);
	}
}


