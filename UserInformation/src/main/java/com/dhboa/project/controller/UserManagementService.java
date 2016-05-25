package com.dhboa.project.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dhboa.project.entity.User;

@Service
public class UserManagementService extends AutowiredUtilObjects{

	public List<User> synchronizeUsers(List<User> users, boolean isInsertable) throws Exception {
		
		if (isInsertable) {
			PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
			for (User userObj : users)
				userObj.setPassword(pwdEncoder.encode(userObj.getPassword()));
		}
		return userRep.save(users);
	}
}
