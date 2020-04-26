package com.itlize.codingtest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itlize.codingtest.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private Map<Character, Character[]> keypadMap;

	@Override
	public int getTotalPhoneCount(String number) {
		if(number == null || number.length() == 0) {
			return 0;
		}
		int res = 1;
		for(int i = 0; i < number.length(); i ++) {
			if (number.charAt(i) == '0' || number.charAt(i) == '1') {
				continue;
			} else if (number.charAt(i) == '9' || number.charAt(i) == '7') {
				res *= 5;
			} else {
				res *= 4;
			}
		}
		return res;
	}

	@Override
	public List<String> getItems(String number, int pageSize, int pageIndex) {
		int startPoint = pageSize * pageIndex;
		if(pageSize == 0 || number == null || number.isEmpty() || startPoint >= getTotalPhoneCount(number)) {
			return new ArrayList<>();
		}
		
		StringBuilder startnumbersb = new StringBuilder();
		int[] position = new int[number.length()];
		for(int digit = number.length() - 1; digit >= 0; digit --) {
			int pos = startPoint % keypadMap.get(number.charAt(digit)).length;
			startPoint = startPoint / keypadMap.get(number.charAt(digit)).length;
			position[digit] = pos;
			startnumbersb.append(keypadMap.get(number.charAt(digit))[pos]);
		}
		StringBuilder resBuilder = startnumbersb.reverse();
		
		List<String> res = new ArrayList<>();
		outerloop:
		for (int i = 0; i < pageSize; i ++) {
			res.add(resBuilder.toString());
			int j = resBuilder.length() - 1;
			while (true) {
				if (j < 0) {
					break outerloop;
				}
				Character[] chars = keypadMap.get(number.charAt(j));
				if (position[j] == chars.length - 1) {
					position[j] = 0;
					resBuilder.setCharAt(j, chars[0]);
					j -= 1;
				} else {
					position[j] += 1;
					resBuilder.setCharAt(j, chars[position[j]]);
					break;
				}	
			}
		}
		return res;
	}
}
