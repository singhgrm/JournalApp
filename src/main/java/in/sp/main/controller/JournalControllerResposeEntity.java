package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import in.sp.main.service.Services;
import java.util.*;
@RestController
@RequestMapping("/journal_info")
public class JournalControllerResposeEntity {
	@Autowired
	Services myservice;
	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
	{
	//the data which have been taken from postman in json formate that is stoted cutrently in @RequestBody JournalEntry myEntry in form of java object
	try
	{
		myservice.CreateJournal(myEntry);
		System.out.println("user  saved in db");
	  return new ResponseEntity(myEntry ,HttpStatus.CREATED);
	}
	
	catch(Exception e)
	{
		System.out.println("user cant save in db");
		  return new ResponseEntity(myEntry ,HttpStatus.BAD_REQUEST);
	}
	}

	//giving data more than 2 userd..
	@PostMapping("/bulk")
	public ResponseEntity<JournalEntry> createEntries(@RequestBody List<JournalEntry> entries) {
	    for (JournalEntry entry : entries) {
	    	
	    	try
	    	{
	    		myservice.CreateJournal(entry);
	    		System.out.println("user cant save in db");
	    	  return new ResponseEntity(entry ,HttpStatus.CREATED);
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println("user cant save in db");
	    		  return new ResponseEntity(entry ,HttpStatus.BAD_REQUEST);
	    	}
	       // myservice.CreateJournal(entry);
	    }
		System.out.println("more than one journal cant save in db ");
 		  return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	@GetMapping
	public List<JournalEntry>  getAllRecord()
	{
		return myservice.getAllJournal();
	}

	@GetMapping("/id/{myid}")
	public ResponseEntity<JournalEntry> GetJournalEntryById(@PathVariable String myid)
	{
	   Optional<JournalEntry>res= myservice.getJournalEntryById(myid);
	   if(res.isPresent())
	   {
		   return new ResponseEntity<>(res.get() , HttpStatus.OK);
	   }
	   
	   //404 error
	   return new ResponseEntity<>(res.get() , HttpStatus.NOT_FOUND);
	   
	}
	
	//
	@DeleteMapping("/id/{myid}")
	public ResponseEntity<?> DeleteJournalEntryById(@PathVariable String myid)
	{
		try
		{
		myservice.deleteJournalEntryById(myid);
		System.out.println("user deleted from db by id");
		  return new ResponseEntity(myid ,HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			System.out.println("user cant deleted from db by id");
			
			  return new ResponseEntity(myid ,HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}


	@DeleteMapping("/id")
public ResponseEntity<?> DeleteJournalEntryAll()
	{
		  try
			{
			 myservice.deleteJournalEntry();
			System.out.println("all user deleted from db ");
			  return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			catch(Exception e)
			{
				System.out.println("all user cant deleted from db ");
				
				  return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			}
	}

	@PutMapping("/{id}")
	public ResponseEntity<JournalEntry> UpdateUserDetails(@PathVariable String id , @RequestBody JournalEntry updated_journal) {
		JournalEntry old_journal= myservice.getJournalEntryById(id).orElse(null);
		
	if(old_journal !=null)
		{	
	old_journal.setTitle(updated_journal.getTitle()!=null  ? updated_journal.getTitle() : old_journal.getTitle());
	old_journal.setContent(updated_journal.getContent()!=null && updated_journal.getContent().equals("")? updated_journal.getContent() : old_journal.getContent());	
	myservice.CreateJournal(old_journal, null);
	System.out.println("update done db ");
		return new ResponseEntity<>(old_journal ,HttpStatus.OK);
		}
	System.out.println("cant update the record for this id");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
