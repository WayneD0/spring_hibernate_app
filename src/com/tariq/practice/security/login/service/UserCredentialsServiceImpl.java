package com.tariq.practice.security.login.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tariq.practice.security.login.model.User;
import com.tariq.practice.security.login.model.Users;

@Service("userCredentialsService")
public class UserCredentialsServiceImpl  implements UserCredentialsService{

	@Autowired
	HttpSession session;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings({  "unchecked" })
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public User authenticateUser(String userName, String password) {
		User user = null;
		try{
			List<Users> securityUsersList = sessionFactory.getCurrentSession().createQuery("select username, password, active from users where username=:userName").setParameter(
					"userName", userName).list();
			if (securityUsersList.size() > 0) {
				user = new User(userName, securityUsersList.get(0).getPassword());
				user.setPassword(securityUsersList.get(0).getPassword());
				user.setUserId(securityUsersList.get(0).getId());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

}
