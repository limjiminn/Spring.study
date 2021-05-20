package com.beer.shop.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LombokTestClass {
	
	private String id;
	private String name;
	private int age;
	private char gender;
	private boolean check;
	private double height;
}
