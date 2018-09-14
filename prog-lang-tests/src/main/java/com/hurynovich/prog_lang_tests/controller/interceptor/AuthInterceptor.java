package com.hurynovich.prog_lang_tests.controller.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hurynovich.prog_lang_tests.entity.User;
import com.hurynovich.prog_lang_tests.entity.UserRole;

public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	@Qualifier("authUris")
	private List<String> authUris;
	
	private final String accessDeniedPage = "access_denied_page";
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
		Object handler) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		if (authUris.contains(requestUri)) {
			Object userAttr = request.getSession().getAttribute("user");
			if ((userAttr != null) && (((User) userAttr).getRole().equals(UserRole.ADMIN))) {
				return true;
			} else {
				request.getRequestDispatcher(accessDeniedPage).forward(request, response);
				return false;
			}
		} else {
			return true;
		}
	}
}
