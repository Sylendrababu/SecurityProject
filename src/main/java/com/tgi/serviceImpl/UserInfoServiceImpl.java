package com.tgi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgi.entity.UserInfo;
import com.tgi.repository.UserInfoRepository;
import com.tgi.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserInfo addUser(UserInfo userInfo) {
	    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	    userInfoRepo.save(userInfo);
	    return userInfo; 
	}
	
}
