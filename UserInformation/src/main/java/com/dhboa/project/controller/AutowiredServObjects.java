package com.dhboa.project.controller;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AutowiredServObjects {
	
	@Autowired
	protected UserManagementService userMgmtServ;

}
