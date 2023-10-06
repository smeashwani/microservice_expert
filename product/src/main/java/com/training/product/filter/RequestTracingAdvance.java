package com.training.product.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(2)
@Slf4j
public class RequestTracingAdvance extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		log.info("RequestTracingAdvance - Request URI is: " + httpServletRequest.getRequestURI());
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		log.info("RequestTracingAdvance - Response Status Code is: " + httpServletResponse.getStatus());
	}
}