package com.hsb.mvpmsassistant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hsb.mvpmsassistant.annotation.JwtIgnore;
import com.hsb.mvpmsassistant.exception.MvpMsAssistantException;
import com.hsb.mvpmsassistant.exception.MvpMsAssistantExceptionCode;
import com.hsb.mvpmsassistant.util.JwtTokenUtil;

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
	private RedisTemplate<String, Object> redisTemplate;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ignore validate by JwtIgnore annation
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }

        String requestUri = request.getRequestURI();
        if(requestUri.startsWith(request.getContextPath())) {
            requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
        }

        if(requestUri.equals("/") 
        		|| requestUri.indexOf("/webjars/") > -1
        		|| requestUri.indexOf("/swagger-resources") > -1
        		|| requestUri.indexOf("/error") > -1
        		|| requestUri.indexOf("/csrf") > -1
        		|| requestUri.indexOf("/swagger-ui.html") > -1) {
               return true;
        }
        
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // get authorization info from header
        final String authHeader = request.getHeader(authHeaderKey);
        logger.info("## authHeader= {}", authHeader);

        if (StrUtil.isBlank(authHeader) || !authHeader.startsWith(tokenPrefix)) {
        	logger.info("### user do not login，please login first ###");
            throw new MvpMsAssistantException(MvpMsAssistantExceptionCode.ERROR_998_01);
        }

        // get token
        final String token = authHeader.substring(7);

        // check if token is valid, if token is valid and then throw excepiton
        JwtTokenUtil.parseJWT(token, base64Secret);
        
        String userId = JwtTokenUtil.getUserId(token, base64Secret);
        String redisToken = (String) redisTemplate.opsForValue().get(userId);
		if (redisToken == null || !redisToken.equals(token)) {
			logger.info("token is invalid, please login again");
			throw new MvpMsAssistantException(MvpMsAssistantExceptionCode.ERROR_998_02);
		}

        return true;
    }

}
