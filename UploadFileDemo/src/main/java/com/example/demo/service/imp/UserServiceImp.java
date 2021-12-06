package com.example.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Service
public class UserServiceImp implements UserService{
    
	/* User Repository **/
	@Autowired
	private UserRepository userRepository;
	
	/* Role Repository **/
	@Autowired
	private RoleRepository  roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user, roleRepository.findById(user.getRoleId()));
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
	@Override
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user,roleRepository.findById(user.getRoleId()));
    }
 
    @Override
    public User addRole(User user, Role role) {
    	user.setRoleId(role.getId());
    	return user;
    }

}
