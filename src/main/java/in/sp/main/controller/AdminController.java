package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entities.User;
import in.sp.main.service.UserService;
import in.sp.main.service.UserServiceImpl;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired 
	private UserService userService;
	
	@GetMapping("/all-users")
	public ResponseEntity<List<User>> geAllUsers()
	{
		List<User>users= userService.getAllUser();
		if(users !=null && !users.isEmpty())
		{
			System.out.println("user not found");
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		System.out.println("no users found");
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/create-admin-user")
	public ResponseEntity<?> createUser(@RequestBody User user)
	{
		userService.saveAdmin(user);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
