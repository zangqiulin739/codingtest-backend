package com.itlize.codingtest.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhoneNumber {
	@NotNull
	@NotEmpty
    @Pattern(
    		regexp="^[+-]?[\\s]?(([0-9]{3})?[\\s-]?[0-9]{3}[\\s-]?[0-9]{4}\\s*$|\\(\\s?[0-9]{3}\\s?\\)[\\s-]?[0-9]{3}[\\s-]?[0-9]{4}\\s*$)",
    		message="Must Enter a valid 7-digit or 10-digit phone number.")
	private String number;
	
	public String format() {
		return number.replaceAll("\\D+","");
	}
}
