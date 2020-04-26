package com.itlize.codingtest.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PhoneService {
	
	int getTotalPhoneCount(String number);
		
	List<String> getItems(String number, int pageSize, int pageIndex);
}
