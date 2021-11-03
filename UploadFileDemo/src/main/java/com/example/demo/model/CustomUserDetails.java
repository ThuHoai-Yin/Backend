package com.example.demo.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.service.impl.RoleService;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	Optional<Role> role;
    private GrantedAuthority authorities;

    public CustomUserDetails(User user, Optional<Role> role) {
		super();
		this.user = user;
		this.role=role;
		// Generate role in database to Authorities in Spring Security
	    if(role!=null) {authorities = new SimpleGrantedAuthority(role.get().getName());}
		
       
	}
 
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




	public void setAuthorities(GrantedAuthority authorities) {
		this.authorities = authorities;
	}

	
	/**
	 * Add role to user 
	 * 
	 * @param role
	 */
	public void addRoleToUser(Role roleChange) {
		user.setRoleId(roleChange.getId());
		  if(roleChange!=null) {
			  setAuthorities(new SimpleGrantedAuthority(roleChange.getName()));}
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Collection<GrantedAuthority> authority= new ArrayList<>();
		 authority.add(authorities);
		return authority;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [user=" + user + ", role=" + role + ", authorities=" + authorities + "]";
	}
	
}
