package com.tariq.practice.common.web;

import java.util.ArrayList;
import java.util.Collection;


import java.util.List;

import javacryption.aes.AesCtr;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import com.tariq.practice.security.login.model.User;
import com.tariq.practice.security.login.service.UserCredentialsService;



public class UserAuthenticationProvider implements Authentication{
	private static final long serialVersionUID = 1L;
	
	@Autowired HttpSession session;
	@Autowired PasswordEncoder passwordEncoder;
	
	@Autowired UserCredentialsService userCredentialsService;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String userName = token.getName();String key="";
		
		String jCryption=(String)token.getCredentials();
		key = (String) session.getAttribute("jCryptionKey");
		if(key==null || key.equals(""))
		{
			throw new PreAuthenticatedCredentialsNotFoundException("");
		}
		String passwordStr = AesCtr.decrypt(jCryption, key, 256); // added jar javacryption-1.0.jar
		String password = passwordEncoder.encode(passwordStr);
		
		User user=null;
		user = userCredentialsService.authenticateUser(userName, password);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}else{
			if(!passwordEncoder.matches(passwordStr, user.getPassword())){
				throw new BadCredentialsException("Invalid username/password");
			}
		}
		session.setAttribute("user", user);
		return new UsernamePasswordAuthenticationToken(user,password,getAuthorities(1));
	}
	
	
	
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {	
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));		
		return authList;	
	}
	
	/**
	
	* Converts a numerical role to an equivalent list of roles	
	* @param role the numerical role	
	* @return list of roles as as a list of {@link String}	
	*/
	
	public List<String> getRoles(Integer role) {	
		List<String> roles = new ArrayList<String>();		
		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}	
		return roles;	
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {	
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));		
		}	
		return authorities;	
	}
	
	
	
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
