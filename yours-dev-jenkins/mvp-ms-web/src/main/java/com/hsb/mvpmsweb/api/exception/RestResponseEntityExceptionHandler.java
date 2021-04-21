package com.hsb.mvpmsweb.api.exception;

import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hsb.mvpmsweb.model.payload.ExceptionResponse;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import cn.hutool.core.util.StrUtil;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public static void main(String[] args) {
		String condition = "'stock-GOOG'  topics || 'industry-tech' in topics";
        boolean in = condition.contains("in");
        System.out.println(in);
	}
	
	@Autowired
	MessageSource exceptionMessageSource;
	
	@Autowired
	MessageSource validationMessageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		String exceptionMessage = result.getAllErrors().stream()
				.map(objectError -> validationMessageSource.getMessage(objectError.getDefaultMessage(), null, request.getLocale())).collect(Collectors.joining("/"));
		String exCode = MvpWebExceptionCode.ERROR_999_98;
		ResponseResult exceptionResult = new ResponseResult(exCode, exceptionMessageSource.getMessage(exCode, new Object[]{ exceptionMessage }, request.getLocale()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse,  HttpStatus.OK);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ExceptionResponse> handleExceptions(final Exception ex, Locale locale) {
		logger.error("Unexpected Exception:", ex);
		
		String code = MvpWebExceptionCode.ERROR_999_99;
		Object[] args = new Object[2];
		args[0] = ex.getClass();
		args[1] = ex.getMessage();
		String exMessage = exceptionMessageSource.getMessage(code, args, locale);
		
		ResponseResult exceptionResult = new ResponseResult(code, exMessage);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(value = { MvpWebException.class })
    public ResponseEntity<ExceptionResponse> handleMvpWebException(MvpWebException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	logger.error("MvpWebException:{" + code + "}-{" + message + "}", ex);
    	
        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { MvpUserException.class })
    public ResponseEntity<ExceptionResponse> handleMvpUserException(MvpUserException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	logger.error("MvpUserException:{" + code + "}-{" + message + "}", ex);

        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { MvpShareException.class })
    public ResponseEntity<ExceptionResponse> handleMvpShareException(MvpShareException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	logger.error("MvpShareException:{" + code + "}-{" + message + "}", ex);

        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { MvpMessageException.class })
    public ResponseEntity<ExceptionResponse> handleMvpMessageException(MvpMessageException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	logger.error("MvpMessageException:{" + code + "}-{" + message + "}", ex);

        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { MvpAssistantException.class })
    public ResponseEntity<ExceptionResponse> handleMvpAssistantException(MvpAssistantException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	logger.error("MvpAssistantException:{" + code + "}-{" + message + "}", ex);
    	
        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { MvpTicketException.class })
    public ResponseEntity<ExceptionResponse> handleMvpTicketException(MvpTicketException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	logger.error("MvpTicketException:{" + code + "}-{" + message + "}", ex);
    	
        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
    }
	
}
