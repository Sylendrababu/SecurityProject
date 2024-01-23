package com.tgi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.dto.AuthenRequest;
import com.tgi.entity.UserInfo;
import com.tgi.service.JwtService;
import com.tgi.service.UserInfoService;

@Configuration
@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {

	 @Autowired
	    UserInfoService userInfoService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//Anyone can add so bypass the authentication	
	@PostMapping("/save")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

	@PostMapping("/authenticate")
	public String AuthenticateAndToken(@RequestBody AuthenRequest authenRequest) {
	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenRequest.getUserName(), authenRequest.getPassword()));
	
	if(authentication.isAuthenticated()) {
		return jwtService.generateToken(authenRequest.getUserName());
	}
	else {
		throw new UsernameNotFoundException("Invalid user request...");
	}
		
		
	}
	
	
}
