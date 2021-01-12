package com.tariq.practice.common.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AuthFilter implements Filter{
	String host;
	@Autowired
	Environment env;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		try {
			this.host = config.getInitParameter("host");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse res,FilterChain next) throws IOException, ServletException {
		try{
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) res;

			int hostLength = host.length();
			String guardCode = null;
			String refererTrimmed = null;

			if (req.getParameter("reqtrack") != null)
				guardCode = req.getParameter("reqtrack");
			else if (req.getHeader("reqtrack") != null)
				guardCode = req.getHeader("reqtrack");

			String trackerId = (String) req.getSession().getAttribute("TRACKERID");
			
			if (req.getHeader("Referer") != null)
				refererTrimmed = req.getHeader("Referer").substring(0, hostLength);
			else
				refererTrimmed = host;
			
			if (host.equals(refererTrimmed)) {
				String loginUrlPattern = host;
				int loginUrlPatternLength = loginUrlPattern.length();
				String actionMapping = req.getRequestURL().toString().substring(loginUrlPatternLength + 1);
				String urlTrimmed = req.getRequestURL().toString().substring(0, loginUrlPatternLength);
				if (loginUrlPattern.equals(urlTrimmed)) {
					if (actionMapping.equals("masters/department/save.spr") || actionMapping.equals("masters/department/delete.spr")) {
						if (isTokenValid(req, "A")) {
							next.doFilter(req, resp);

						} else {
							this.setErrorPage(req, resp);
						}

					} else {
						if (!actionMapping.equals("email/getNewEmail.spr"))

						{
							HttpSession session = null;
							session = req.getSession();
							setToken(session);
						}
						this.setNoCache(req, resp);
						next.doFilter(req, resp);
					}
				}else {

					this.setErrorPage(req, resp);
					throw new ServletException("Unauthorized access");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private boolean isTokenValid(HttpServletRequest request, String aevFlag) {

		try {
			if (request.getAttribute("VALIDATED") != null) {
				return true;
			}
			HttpSession session = null;
			session = request.getSession();

			String reqToken = request.getParameter("reqtrack");

			if (reqToken != null && (reqToken.trim()).equals(session.getAttribute("TRACKERID"))) {
				session.setAttribute("TRACKERID1", session.getAttribute("TRACKERID"));
				setToken(session);
				request.setAttribute("VALIDATED", "true");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void setToken(HttpSession session) {
		try {
			String token = RandomStringUtils.randomAlphanumeric(25);
			session.setAttribute("TRACKERID", token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setErrorPage(HttpServletRequest request, ServletResponse res) throws IOException {

		try {
			String scheme = request.getScheme(); // http
			String serverName = request.getServerName(); // hostname.com
			int serverPort = request.getServerPort(); // 80
			String contextPath = request.getContextPath();
			String url = scheme + "://" + serverName + ":" + serverPort + contextPath;

			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			out.println("<HTML>");
			out.println("<HEAD><TITLE>Error</TITLE></HEAD>");
			out.println("<BODY><br /><br /><br /><br /><br /><br />");
			out.println("<CENTER><BIG>Invalid Request.</BIG></CENTER>");
			out.println("</BODY></HTML>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setNoCache(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
				response.setHeader("Pragma", "no-cache");
			} else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
				response.setHeader("Cache-Control", "no-cache");
			}
			this.killCookies(request, response);
			response.setDateHeader("Expires", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void killCookies(HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie[] allCookies;
			allCookies = request.getCookies();
			if (allCookies != null) {
				for (int i = 0; i < allCookies.length; i++) {
					Cookie cookie = allCookies[i];
					cookie.setValue("");
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
