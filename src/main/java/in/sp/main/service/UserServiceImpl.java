package in.sp.main.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import in.sp.main.entities.User;
import in.sp.main.repository.UserRepository;
@Service
@Component
public class UserServiceImpl  implements UserService
{
	
@Autowired
UserRepository userRepository;

private static final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

private static final BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
	@Override
	public boolean SaveUser(User user)
	{
		// TODO Auto-generated method stub
//		if (user.getId() == null) {
//	        user.setId(UUID.randomUUID().toString()); // unique value
//	    }
		
		userRepository.save(user);
log.info("diirent user have beem save in db");
		return true;
	}
	
	public void saveNewUser(User user)
{
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setRoles(Arrays.asList("USER"));
//	userRepository.save(user);
	
//	try
//	{
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	user.setRoles(Arrays.asList("USER"));
userRepository.save(user);

log.info("user have been saved in db successfully "+user.getUsername());
	
//	}
//	
//	catch(Exception e)
//	{
//		log.error("error occured while saving the users "+user.getUsername()+"  , "+e);
//		
//	}
	
	
	}
	
	//this fujction  have been used in unit testing
	public boolean saveNewUser1(User user)
	{
		boolean find=false;
		
		try
		{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
	userRepository.save(user);
	find=true;
	
	System.out.println("user have been saved in db successfully "+user.getUsername());
	return true;
		
		}
		
		catch(Exception e)
		{
			System.out.println("error occured while saving the users "+user.getUsername()+"  , "+e);
			
		}
		return find;
	}
	
public void saveAdmin(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
	userRepository.save(user);
	
	}
	
	public void saveUser(User user)
	{
		userRepository.save(user);
	}
	

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserByUsername(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(name);
	
	}

	@Override
	public User   updateUserEntry(ObjectId id, User user) {
		// TODO Auto-generated method stub
	//before updating user first find the user that user exist or not so find user bu is..
		User user_info=userRepository.findById(id).orElse(null);
		if(user_info !=null)
		{
	System.out.println("user find with this id = "+id +" in the database");
	return 	userRepository.save(user);
		}
	throw new RuntimeException("user does not exist with this id "+id);
	}

	@Override
	public void deleteUserEntry() {
		// TODO Auto-generated method stub
		userRepository.deleteAll();
		
	}

	@Override
	public void deleteUserEntryByName(String name)
	{
		// TODO Auto-generated method stub

	userRepository.deleteByUsername(name);
	}

	
public User findDeatailsByUserName(String user_name)
	{
		//it will return user if user exist
return userRepository.findByUsername(user_name);
	
	}
	
}
