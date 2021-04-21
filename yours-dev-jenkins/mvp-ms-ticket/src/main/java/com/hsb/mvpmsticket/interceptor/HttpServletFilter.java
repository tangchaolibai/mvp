package com.hsb.mvpmsticket.interceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.StrUtil;

public class HttpServletFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> map = new HashMap<>(request.getParameterMap());
		if(request instanceof HttpServletRequest) {
			Cookie[] cookies =  ((HttpServletRequest)request).getCookies();
			String cityName = null;
			if(cookies != null) {
				cityName = Arrays.stream(cookies).filter(cookie -> "city".equals(cookie.getName())).map(cookie -> cookie.getValue()).findFirst()
						.orElseGet(() -> ((HttpServletRequest)request).getHeader("city"));
			} else {
				cityName = ((HttpServletRequest)request).getHeader("city");
			}
			if(StrUtil.isBlank(cityName)) cityName = request.getParameter("city");
			map.put("city", new String[]{cityName});
		} 
        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper((HttpServletRequest) request, map);
        //继续向后传递修改后的request,拦截器不能实现。
        chain.doFilter(parameterRequestWrapper, response);
	}
	
}
