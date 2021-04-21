package com.hsb.mvpmsweb.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hsb.mvpmsweb.api.annotation.JwtIgnore;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.api.exception.MvpWebExceptionCode;
import com.hsb.mvpmsweb.util.JwtTokenUtil;
import com.hsb.mvpmsweb.util.RedisUtils;

import cn.hutool.core.util.StrUtil;

/**
 * Token Validation Interceptor
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	@Value("${jwt.base64Secret}")
	private String base64Secret;
	
	@Value("${jwt.auth-header-key}")
    private String authHeaderKey;
    
    @Value("${jwt.token-prefix}")
    private String tokenPrefix;
	
	@Autowired
	private RedisUtils redisUtils;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ignore validate by JwtIgnore annotation
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }

        String requestUri = request.getRequestURI();
        logger.info("requestUri: {}", requestUri);
        if(requestUri.startsWith(request.getContextPath())) {
            requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
        }

        if(requestUri.equals("/") 
        		|| StrUtil.containsIgnoreCase(requestUri, "webjars")
        		|| StrUtil.containsIgnoreCase(requestUri, "swagger")
        		|| StrUtil.containsIgnoreCase(requestUri, "error")
        		|| StrUtil.containsIgnoreCase(requestUri, "csrf")
        		|| StrUtil.containsIgnoreCase(requestUri, "favicon.ico")
        		|| StrUtil.containsIgnoreCase(requestUri, "api-docs-ext")
        		|| StrUtil.containsIgnoreCase(requestUri, "doc.html")
        		|| StrUtil.containsIgnoreCase(requestUri, "test1.html")
        		|| StrUtil.containsIgnoreCase(requestUri, "apple-app-site-association")) {
               return true;
        }
        
        if (HttpMethod.OPTIONS.equals(HttpMethod.valueOf(request.getMethod()))) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // get authorization info from header
        final String authHeader = request.getHeader(authHeaderKey);
        logger.info("## authHeader= {}", authHeader);

        if (StrUtil.isBlank(authHeader) || !StrUtil.startWith(authHeader, tokenPrefix)) {
        	logger.info("### user do not login，please login first ###");
            throw new MvpWebException(MvpWebExceptionCode.ERROR_998_01);
        }

        // get token
        final String token = StrUtil.subSuf(authHeader, 7);

        // check if token is valid, if token is valid and then throw excepiton
        JwtTokenUtil.parseJWT(token, base64Secret);
        
        String userId = JwtTokenUtil.getUserId(token, base64Secret);
        String redisToken = (String) redisUtils.get(userId);
		if (redisToken == null || !redisToken.equals(token)) {
			logger.info("Token is invalid, please login again!");
			throw new MvpWebException(MvpWebExceptionCode.ERROR_998_02);
		}

        return true;
    }
    
}
