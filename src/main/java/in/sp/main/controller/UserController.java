package in.sp.main.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entities.User;
import in.sp.main.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
@RestController
@RequestMapping("/user")
public class UserController 
{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
//dsave user details into database ,this same have been used in public controller
//	@PostMapping
//public ResponseEntity<User> createUser( @RequestBody User user)
//  {	
//		
//	try
//	  {
//		//userService.SaveUser(user);
//userService.saveNewUser(user);
//	System.out.println("user information saved");	
//	return new ResponseEntity<>(user, HttpStatus.CREATED);
//	  
//	  }
//	 
//   catch(Exception e)
//	{
//		 System.out.println("cant save data in db");
//e.printStackTrace();
//		 System.out.println("cant save data in db");
//
//		 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//	}
//
//}
	
	
	
	//this is update user so we will apply here authentication
		@PutMapping
	public ResponseEntity<?> updateUserDeatails(@RequestBody User updated_user)
	{
	//this updated user will come from postman
	Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
	String user_name=authentication.getName();	
	
	User userInDB=userService.findDeatailsByUserName(user_name);
	//now we need to update user name and password
		
			userInDB.setUsername(updated_user.getUsername());
			userInDB.setPassword(updated_user.getPassword());

		userService.saveNewUser(userInDB);
		//	userService.SaveUser(userInDB);
	//now updated user is saves in db
			System.out.println("user updated in db "+updated_user);
	return new ResponseEntity(userInDB, HttpStatus.OK);
	
		
	}	
	
	//this method is done by encryption and dycyption methos..

		//delete user details by id from database
			@DeleteMapping
		public ResponseEntity<?> DeleteUserDetailsByName()
		{
	Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
	String user_name=authentication.getName();
	
			userService.deleteUserEntryByName(user_name);
			System.out.println("user record deleted with name "+ user_name);
		   return new  ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
	

	@PostMapping("/all")
public ResponseEntity<List<User>> saveManyusers(@RequestBody List<User> users)
{
	for(User user: users)
	{
				userService.SaveUser(user);
				
	}
	System.out.println("all users information saved");	
	return new ResponseEntity(users, HttpStatus.CREATED);
}



//get user  by id
//dsave user detauks into database
	@GetMapping("/id/{name}")
public ResponseEntity<User> getUserDetailsByNmae(@PathVariable String name)
{
User user=	userService.getUserByUsername(name);
if(user!=null)
{
	System.out.println("user found with name "+name);
	return new ResponseEntity<>(user,HttpStatus.OK);

}
System.out.println("user does not found with name "+name);
return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
}




//get all users from database database
	@GetMapping
public ResponseEntity<User> getAllUserDetails()
{
	List<User>users=userService.getAllUser();
	if(users.size()>=1)
	{
		System.out.println("all user data is received");
		return new ResponseEntity(users,HttpStatus.OK);
	}
	System.out.println("cant fetch the data");

	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	
}


	

//	//delete user details by id from database
//		@DeleteMapping("/name/{myname}")
//	public ResponseEntity<?> DeleteUserDetailsByName(@PathVariable String myname)
//	{
//		try
//		{
//		userService.deleteUserEntryByName(myname);
//		System.out.println("user record deleted with name "+ myname);
//	   return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		catch(Exception e)
//		{
//			System.out.println("user record  does not found with name "+ myname);
//			e.printStackTrace();
//		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//		}
//		
//	}
	
	
	







//delte all user details from database
//	@DeleteMapping
//public ResponseEntity<?> deleteAllUserDetails()
//{
//	try
//	{
//	userService.deleteUserEntry();
//	return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//	catch(Exception e)
//	{
//		e.printStackTrace();
//	return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//	}
	
//}





	@PutMapping("/objectid/{myid}")
public ResponseEntity<User> updateUserDeatailsWay2(@PathVariable ObjectId myid,@RequestBody User updated_user)
{
	updated_user=userService.updateUserEntry(myid,updated_user);
	if(updated_user !=null)
	{
		return new ResponseEntity(updated_user, HttpStatus.OK);
	}
	System.out.println("cant update the record for this id");
	
	return new ResponseEntity(HttpStatus.NOT_FOUND);
	
}

	
//	@PutMapping("/{user_name}")
//public ResponseEntity<User> updateUserDeatailsWay3(@RequestBody User updated_user, @PathVariable String user_name)
//{
////this updated user will come from postman	
//	User userInDB=userService.findDeatailsByUserName(user_name);
////now we need to update user name and password
//	if(userInDB != null)
//	{
//		userInDB.setUsername(updated_user.getUsername());
//		userInDB.setPassword(updated_user.getPassword());
//	//now we need to save this updateduser in db
//		userService.SaveUser(userInDB);
////now updated user is saves in db
//		System.out.println("user updated in db "+updated_user);
//return new ResponseEntity(userInDB, HttpStatus.OK);
//}
//	
//System.out.println("cant update the record for this name= "+updated_user);
//	
//	return new ResponseEntity(HttpStatus.NO_CONTENT);
//	
//}


}