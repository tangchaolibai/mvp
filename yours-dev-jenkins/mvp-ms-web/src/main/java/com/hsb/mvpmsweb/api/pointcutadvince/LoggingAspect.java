package com.hsb.mvpmsweb.api.pointcutadvince;

import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("target(com.hsb.mvpmsweb.api.MvpAssistantModuleApi)")
	public void logMvpAssistantModuleApi() {}
	
	@Pointcut("target(com.hsb.mvpmsweb.api.MvpMessageModuleApi)")
	public void logMvpMessageModuleApi() {}
	
	@Pointcut("target(com.hsb.mvpmsweb.api.MvpShareModuleApi)")
	public void logMvpShareModuleApi() {}
	
	@Pointcut("target(com.hsb.mvpmsweb.api.MvpUserModuleApi)")
	public void logMvpUserModuleApi() {}
	
	@Before("logMvpAssistantModuleApi() || logMvpMessageModuleApi() || logMvpShareModuleApi() || logMvpUserModuleApi()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("========================Request Detail================================");
		logger.info("Remote Addr: {}", request.getRemoteAddr());
		logger.info("HTTP Method: {}", request.getMethod());
		logger.info("Request URL: {}", request.getRequestURL());
		logger.info("Request Headers:");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			logger.info("[{}]-[{}]", headerName, request.getHeader(headerName));
		}
		Signature signature = joinPoint.getSignature();
		if(signature instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature) signature;
			logger.info("{}.{}({})", joinPoint.getTarget().getClass().getName(), signature.getName(),
					methodSignature.getParameterNames());
			for(int i = 0; i < methodSignature.getParameterNames().length; i++) {
				logger.info("{} : {}", methodSignature.getParameterNames()[i], joinPoint.getArgs()[i]);
			}
		}
		
//		// Traversing print input parameters
//		logger.info("Request Parameters :");
//		for (Object object : joinPoint.getArgs()) {
//			Class<? extends Object> clazz = object.getClass();
//			Field[] fields = clazz.getDeclaredFields();
//			Stream.of(fields).forEach(field -> {
//				field.setAccessible(true);
//					try {
//						logger.info("{}:{}", field.getName(), field.get(object));
//					} catch (IllegalArgumentException | IllegalAccessException e) {
//						logger.error("Error when logging request parameters");
//						logger.error(e.getMessage());
//					}});
//		}
		logger.info("======================================================================");
	}
	
	@AfterReturning(returning = "responseEntity", pointcut = "logMvpAssistantModuleApi() || logMvpMessageModuleApi() || logMvpShareModuleApi() || logMvpUserModuleApi()")
	public void doAfterReturning(Object responseEntity) {
		logger.info("========================Response Detail================================");
		if (responseEntity instanceof ResponseEntity) {
			logger.info("status:{}", ((ResponseEntity<?>) responseEntity).getStatusCodeValue());
			if(responseEntity instanceof HttpEntity<?>) {
				Object response = ((HttpEntity<?>)responseEntity).getBody();
				Class<? extends Object> clazz = response.getClass();
				logResponseDetail(response, clazz);
			}
		}
		logger.info("======================================================================");
	}

	private void logResponseDetail(Object response, Class<? extends Object> clazz) {
		if(clazz.getSuperclass() != null && !clazz.getSuperclass().getName().equals("java.lang.Object")) {
			logResponseDetail(response, clazz.getSuperclass());
		}
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			try {
				logger.info("{}:{}", field.getName(), field.get(response));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("Error when logging response parameters.");
				logger.error(e.getMessage());
			}
		}
	}
	
    @Around("execution(* com.hsb.mvpmsweb.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String clazzName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
 
        long start = System.currentTimeMillis();
        logger.info( "{}: {}: start...", clazzName, methodName);
 
        Object result = joinPoint.proceed();
 
        long time = System.currentTimeMillis() - start;
        logger.info( "{}: {}: : end... cost time: {} ms.", clazzName, methodName, time);
 
        return result;
    }

}
