package com.dhboa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhboa.project.entity.UserRepository;

public abstract class AutowiredUtilObjects {
	
	@Autowired
	protected UserRepository userRep;

}
