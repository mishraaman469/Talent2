package com.mindtree.talent.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.talent.exception.entity.ApiError;

@RestControllerAdvice
public class TalentExceptionHandler {
	
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ApiError> globalExceptionHandler(Exception  exception){
			ApiError error=new ApiError(500,exception.getMessage(),new Date());
			return new ResponseEntity<ApiError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
}
