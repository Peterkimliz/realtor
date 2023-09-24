package com.example.digirealtor.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.digirealtor.Exceptions.ExceptionObject;

@RestControllerAdvice
public class ExceptioAdvice {

//   @ExceptionHandler()  
  public ResponseEntity<ExceptionObject> inputsError(){
    ExceptionObject exceptionObject=new ExceptionObject();
    return new ResponseEntity<ExceptionObject>(exceptionObject,HttpStatus.BAD_REQUEST);
  }

    
}
