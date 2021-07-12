package com.mindtree.talent.exception.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

		private int number;
		private String message;
		private Date date;
		
		
		
		
		
}
