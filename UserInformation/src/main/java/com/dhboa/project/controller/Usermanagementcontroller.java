package com.dhboa.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhboa.project.entity.User;
import com.dhboa.project.entity.UserRepository;



@RestController


public class Usermanagementcontroller  extends AutowiredServObjects{
	
	@RequestMapping(value = "/usermanagement/admin/sync", method = RequestMethod.POST)
	public List<User> synchronizeUsers(
			@RequestBody List<User> users,
			@RequestParam(value = "insert", defaultValue = "false") boolean insert)
			throws Exception {
		return userMgmtServ.synchronizeUsers(users, insert);
	}
	
	@Autowired UserRepository userrepo;
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserInfo(Principal user) {
        return userrepo.findOne(user.getName());
    }
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getMessage(){
		return "Helloworld";
		
	}
	
	 

}
