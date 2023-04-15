package com.pranav.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Resource Not Found Exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException exception, WebRequest webRequest)
	{
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
	// Global Exception handling
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> resourceNotFound(Exception exception, WebRequest webRequest)
	{
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// For handling the Custom validation we need to override the method from the ResponseEntityExceptionHandler
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> erros=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String filedName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			erros.put(filedName, message);
		});
		return new ResponseEntity<Object>(erros, HttpStatus.BAD_REQUEST);
	}
	
	// AccessDeniedException
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> accessDeniedException(AccessDeniedException exception, WebRequest webRequest)
	{
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.UNAUTHORIZED);
	}
	
	// UserNotFoundException
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorDetails> usernameNotFoundException(UsernameNotFoundException exception, WebRequest webRequest)
	{
		ErrorDetails error=new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
}

