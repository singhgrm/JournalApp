package in.sp.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/public")
public class PublicController 
{
   
	@Autowired
	UserService userService;
	//we have used Slf4j with class as an annoation so need of 
Logger log=LoggerFactory.getLogger(PublicController.class);	
	
	@GetMapping("/health-check") 
	public String healthCheck()
	{
		return "okay";
	}
	

@PostMapping("/create-user")
public ResponseEntity<User> createUser( @RequestBody User user)
  {	
		
	try
	  {
		//userService.SaveUser(user);
	userService.saveNewUser(user);
	log.info("user information saved  by public controller");	
	return new ResponseEntity<>(user, HttpStatus.CREATED);
	  }
	
	 
   catch(Exception e)
	{
		 log.info("cant save data in db  by public controller");
		 log.warn("cant save data in db  by public controller");

		 log.error("error occurred for {} " , user.getUsername());
		 log.debug("cant save data in db  by public controller");	
		 log.trace("cant save data in db  by public controller");
		
e.printStackTrace();
		// System.out.println("cant save data in db by public controller");

		 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}

}
