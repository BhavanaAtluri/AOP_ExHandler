package com.list.commons;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.list.exception.DeviceNotFoundException;

@ControllerAdvice  
@RestController
public class CustomExceptionHandler {
	
	/*@ExceptionHandler(value=DeviceNotFoundException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new DeviceNotFoundException(status.value(), ex.getMessage()), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }*/
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = DeviceNotFoundException.class)  
    public String handleBaseException(DeviceNotFoundException e){  
        return e.getMessage(); 
    }  
    
    @ExceptionHandler(Exception.class)
	public String handleAllException(HttpServletRequest req, Exception ex) {
    	return "Exception with URL : "+req.getRequestURL().toString() + " , Error Message: "+ex.getMessage();
    }
}
