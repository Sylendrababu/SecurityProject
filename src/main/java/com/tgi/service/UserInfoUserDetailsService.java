package com.tgi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tgi.config.UserInfoUserDetails;
import com.tgi.entity.UserInfo;
import com.tgi.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService{

	 @Autowired
	    private UserInfoRepository userInfoRepo;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<UserInfo> userInfo = userInfoRepo.findByUsername(username);

	        return userInfo.map(UserInfoUserDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

	    }
}
