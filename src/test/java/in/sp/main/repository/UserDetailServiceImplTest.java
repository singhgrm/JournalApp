package in.sp.main.repository;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.assertions.Assertions;

import in.sp.main.entities.User;
import in.sp.main.repository.UserRepository;
import in.sp.main.service.UserDetailServiceImpl;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceImplTest {

@InjectMocks
private UserDetailServiceImpl userDetailService;


@Mock
private	UserRepository userRepository;
	
@Disabled
@BeforeEach 
void setUp()
{
	//this setup methos will run begore every method
	//MockitoAnnotations.initMocks(this);
	MockitoAnnotations.openMocks(this);
}

@Disabled
@Test
void loadUserByUsernameTest()
	{
	
//when(userRepository.findByUsername((ArgumentMatchers.anyString()))
//		.thenReturn(User.builder()
//		.username("garima")
//		.password("garima")
//		.roles(new ArrayList<>())
//		.build());
	 
	   User entity = new User();
	    entity.setUsername("garima");
	    entity.setPassword("garima");
	    entity.setRoles(new ArrayList<>());
	    entity.setJournalEntries(new ArrayList<>());

	    when(userRepository.findByUsername(anyString()))
	            .thenReturn(entity);

	  UserDetails user= userDetailService.loadUserByUsername("garima");
	  Assertions.assertNotNull(user);
	  
	}
}
