package com.hsb.mvpmsshare.exception;

import java.util.List;
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

import com.hsb.mvpmsshare.model.payload.ExceptionResponse;
import com.hsb.mvpmsshare.model.payload.ResponseResult;

import cn.hutool.core.util.StrUtil;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	MessageSource exceptionMessageSource;
	
	@Autowired
	MessageSource validationMessageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<String> errorMessages = result.getAllErrors().stream()
				.map(objectError -> validationMessageSource.getMessage(objectError, request.getLocale()))
				.collect(Collectors.toList());

		String exCode = MvpMsShareExceptionCode.ERROR_999_98;
		String exMessage = exceptionMessageSource.getMessage(exCode, new Object[]{ errorMessages.toString() }, request.getLocale());
		ResponseResult exceptionResult = new ResponseResult(exCode, exMessage);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<Object>(exceptionResponse,  HttpStatus.OK);
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ExceptionResponse> handleExceptions(final Exception ex, Locale locale) {
		logger.error("Unexpected Exception:", ex);
		
		String code = MvpMsShareExceptionCode.ERROR_999_99;
		Object[] args = new Object[2];
		args[0] = ex.getClass();
		args[1] = ex.getMessage();
		String exMessage = exceptionMessageSource.getMessage(code, args, locale);
		
		ResponseResult exceptionResult = new ResponseResult(code, exMessage);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(value = { MvpMsShareException.class })
    public ResponseEntity<ExceptionResponse> handleMvpMsShareException(MvpMsShareException ex, Locale locale) {
    	String code = ex.getCode();
    	String message = ex.getMessage();
    	if(StrUtil.isEmpty(message)) {
    		message = exceptionMessageSource.getMessage(code, ex.getArgs(), locale);
    	}
    	
        ResponseResult exceptionResult = new ResponseResult(code, message);
		ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionResult);
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.OK);
    }
	
}
