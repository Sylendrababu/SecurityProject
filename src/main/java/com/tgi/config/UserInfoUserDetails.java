package com.tgi.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.tgi.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
	private UserInfo userInfo;
	
			private String username;
			private String password;
			private List<GrantedAuthority> authorities;
			
			public UserInfoUserDetails(UserInfo userInfo) {
				username = userInfo.getName();
				password = userInfo.getPassword();
				authorities = Arrays.stream(userInfo.getUserRole().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
	
			}
		
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	/*	Set<UserRole> roles = userInfo.getUserRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        } */
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}
