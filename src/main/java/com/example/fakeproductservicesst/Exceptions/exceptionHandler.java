package com.example.fakeproductservicesst.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<exceptionsDto> handelArthmeticException(Exception ex){
        exceptionsDto excp = new exceptionsDto();
        excp.setMessage(ex.getMessage());
        excp.setDetails("arithmetic exception");
        ResponseEntity<exceptionsDto> responseEntity = new ResponseEntity<>(excp, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<exceptionsDto> handelRuntimeException(Exception ex){
        exceptionsDto excp = new exceptionsDto();
        excp.setMessage(ex.getMessage());
        excp.setDetails("Runtime exception");
        ResponseEntity<exceptionsDto> responseEntity = new ResponseEntity<>(excp, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<exceptionsDto> handelGeneralException(Exception ex){
        exceptionsDto excp = new exceptionsDto();
        excp.setMessage(ex.getMessage());
        excp.setDetails("Unknown exception");
        ResponseEntity<exceptionsDto> responseEntity = new ResponseEntity<>(excp, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<exceptionsDto> handelProductNotFoundException(ProductNotFound ex){
        exceptionsDto excp = new exceptionsDto();
        excp.setMessage("Product not found");
        excp.setDetails("Product Not found exception for "+ex.getId()+" id.");
        ResponseEntity<exceptionsDto> responseEntity = new ResponseEntity<>(excp, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<exceptionsDto> handelCategoryNotFoundException(CategoryNotFound ex){
        exceptionsDto excp = new exceptionsDto();
        excp.setMessage(ex.getMessage());
        excp.setDetails("Product Not found exception for "+ex.getCategory()+" category.");
        ResponseEntity<exceptionsDto> responseEntity = new ResponseEntity<>(excp, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
