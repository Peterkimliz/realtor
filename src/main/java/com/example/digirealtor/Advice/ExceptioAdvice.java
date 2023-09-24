package com.example.digirealtor.Advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.digirealtor.Exceptions.ExceptionObject;
import com.example.digirealtor.Exceptions.FoundException;

@RestControllerAdvice
public class ExceptioAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)  
  public ResponseEntity<ExceptionObject> inputsError(MethodArgumentNotValidException exception){
    ExceptionObject exceptionObject=new ExceptionObject();

    List<ObjectError>errors= exception.getAllErrors();
    for (ObjectError objectError : errors) {
        exceptionObject.setMessage(objectError.getDefaultMessage());
        
    }

    return new ResponseEntity<ExceptionObject>(exceptionObject,HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(FoundException.class)  
  public ResponseEntity<ExceptionObject> foundException(FoundException exception){
    ExceptionObject exceptionObject=new ExceptionObject();
    exceptionObject.setMessage(exception.getMessage());
    return new ResponseEntity<ExceptionObject>(exceptionObject,HttpStatus.BAD_REQUEST);
  }

    
}
