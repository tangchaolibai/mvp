package com.hsb.mvpmsuser.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@SuppressWarnings("unused")
public class UserInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
//		String userId = request.getHeader("userId");
//		String token = request.getHeader("token");
//		if (StringUtils.isBlank(userId) && StringUtils.isBlank(token)) {
//			logger.info("No token");
//			return false;
//		}
//		String redisToken = (String) redisTemplate.opsForValue().get(userId);
//		if (redisToken != null && redisToken.equals(token)) {
//			logger.info("token validate success");
//			return true;
//		} else {
//			logger.info("token validate fail");
//			return false;
//		}
		return true;
	}

}
