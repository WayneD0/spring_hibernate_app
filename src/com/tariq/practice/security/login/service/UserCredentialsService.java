package com.tariq.practice.security.login.service;

import com.tariq.practice.security.login.model.User;

public interface UserCredentialsService {
	public User authenticateUser(String userName, String password);
}
