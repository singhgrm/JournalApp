package in.sp.main.controller;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.sp.main.entities.JournalEntry;
import in.sp.main.entities.User;
import in.sp.main.service.Services;
import in.sp.main.service.UserService;


import java.util.*;
@RestController
@RequestMapping("/user_with_journal")
public class JournalEntryControllerWithUser {
	
	@Autowired
	Services journalEntryService;
//to get user service record like get user and data everything we weill will use Usrservice repository
	
	 @Autowired
	private UserService userService;
	 
	//iss username ke liye hm sari journsal entries pta kr lenge
	@GetMapping
	public ResponseEntity<?>  getAllJournalEntriesOfUser()
	{
//this will take userbame and paswword from basic authenitucar postman if its correct then we will get userdetails		
	Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
	String user_name=authentication.getName();	
		
	User user =	userService.findDeatailsByUserName(user_name);
	//it will take journal entries from user class
	
List<JournalEntry>all=user.getJournalEntries();
	
	if(all !=null && all.size()>=1)
	{
		System.out.println("find all the record for user in journal");
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	
	System.out.println("cant find the  record for journal for user name "+user_name);
	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	
//	List<JournalEntry>alljournal= myservice.getAllJournal();
	}	
	
	//iss function se iss username me sari journal entries ki id aa jayegi ,jo hm entrer kre krege post method se raw and json me
	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
	{
	//the data which have been taken from postman in json formate that is stoted cutrently in @RequestBody JournalEntry myEntry in form of java object
		Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
		String user_name=authentication.getName();	
		
		
	try
	{
		
//		User user=	userService.findDeatailsByUserName(username);
		journalEntryService.CreateJournal(myEntry ,user_name);
		//semyservice.CreateJournal(myEntry,user);
		System.out.println("hoirnal save in db also this journal saved with user "+user_name);
	  return new ResponseEntity(myEntry ,HttpStatus.CREATED);
	}
	
	
	catch(Exception e)
	{
		System.out.println("joirnal entry cant saved ion db with user "+user_name);
		  return new ResponseEntity(myEntry ,HttpStatus.BAD_REQUEST);
	}
	
	}
	

//we get the journal details by user records
	@GetMapping("/id/{myid}")
	public ResponseEntity<JournalEntry> GetJournalEntryById(@PathVariable String myid)
	{
		Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
		String user_name=authentication.getName();	
	//first received  the user name from basix auth
		User user=userService.findDeatailsByUserName(user_name);
		List<JournalEntry>collect=user.getJournalEntries().stream().filter(x-> x.getId().equals(myid)).collect(Collectors.toList()); 
	//here whatever user have been mapped with this journal , we can enter th username and get journal recored..
		if(!collect.isEmpty())
		{
			   Optional<JournalEntry>res= journalEntryService.getJournalEntryById(myid);
			   if(res.isPresent())
			   {
				   
				   return new ResponseEntity<>(res.get() , HttpStatus.OK);
			   
			   }
		}

	   
	  
	   //404 error
	   return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	   }
	
	
	
	
	
//agr journal entry id delete hui to ,ye journal entry user ke journal entries se bhi delete honi chahiye
	@DeleteMapping("/id/{myid}")
	public ResponseEntity<?> DeleteJournalEntryById(@PathVariable String myid)
	{
		Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
		String user_name=authentication.getName();	
		
		
		boolean removed=	journalEntryService.deleteJournalById(myid ,user_name);
		if(removed)
		{
		System.out.println("journal entrues with this  "+ myid +"  delete from user and journal both");
		  return new ResponseEntity(myid ,HttpStatus.NO_CONTENT);
		}
		
		else 
		{
			System.out.println("user cant deleted from db by id "+myid);
			
			  return new ResponseEntity(myid ,HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}




	@PutMapping("/id/{myid}")
	public ResponseEntity<JournalEntry> UpdateJournalDetails(@PathVariable String myid ,
			@RequestBody JournalEntry updated_journal )
	{
		
		Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
		String user_name=authentication.getName();	
		
		User user=userService.findDeatailsByUserName(user_name);
		List<JournalEntry>journal_entries=user.getJournalEntries().stream().filter(x-> x.getId().equals(myid)).collect(Collectors.toList()); 
		if(!journal_entries.isEmpty())
		{
			   Optional<JournalEntry>old_journal= journalEntryService.getJournalEntryById(myid);
			   if(old_journal.isPresent())
			   {
				   JournalEntry	   old=old_journal.get();
				   
				   old.setTitle(updated_journal.getTitle()!=null  ? updated_journal.getTitle() : old.getTitle());
				   old.setContent(updated_journal.getContent()!=null && !updated_journal.getContent().equals("")? updated_journal.getContent() : old.getContent());	
					journalEntryService.CreateJournal(old);
					System.out.println(" journal updarte update done db ");
			return new ResponseEntity<>(old ,HttpStatus.OK); 
			   
			   }
		}
		
	System.out.println("cant update the record for this id");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
//	
	
	//giving data more than 2 userd..
//	@PostMapping("/bulk")
//	public ResponseEntity<JournalEntry> createEntries(@RequestBody List<JournalEntry> entries) {
//	    for (JournalEntry entry : entries) {
//	    	
//	    	try
//	    	{
//	    		journalEntryService.CreateJournal(entry);
//	    		System.out.println("user cant save in db");
//	    	  return new ResponseEntity(entry ,HttpStatus.CREATED);
//	    	}
//	    	catch(Exception e)
//	    	{
//	    		System.out.println("user cant save in db");
//	    		  return new ResponseEntity(entry ,HttpStatus.BAD_REQUEST);
//	    	}
//	       // myservice.CreateJournal(entry);
//	    }
//		System.out.println("more than one journal cant save in db ");
// 		  return new ResponseEntity(HttpStatus.BAD_REQUEST);
//	}
	
	
//	@DeleteMapping("/id")
//public ResponseEntity<?> DeleteJournalEntryAll()
//	{
//		  try
//			{
//			  journalEntryService.deleteJournalEntry();
//			System.out.println("all user deleted from db ");
//			  return new ResponseEntity(HttpStatus.NO_CONTENT);
//			}
//			catch(Exception e)
//			{
//				System.out.println("all user cant deleted from db ");
//				
//				  return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//			}
//	}
}
