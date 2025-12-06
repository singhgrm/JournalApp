package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import in.sp.main.entities.User;
import in.sp.main.repository.UserRepository;
import lombok.Data;
//UserDetailsService this is provoded by java so no need to create UserDetailsService interface beacuse its already provided by java
@Component
@Data
public class UserDetailServiceImpl implements UserDetailsService {

	
	@Autowired
	UserRepository userRepository;
//this method loadUserByUsername is provide by UserDetailsService interface	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepository.findByUsername(username);
		if (user != null) {
		
		UserDetails userDetails=	org.springframework.security.core.userdetails.User.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			return userDetails;
		}
			
		
		    throw new UsernameNotFoundException("User not found with username: " + username);
		
		
	}

}
