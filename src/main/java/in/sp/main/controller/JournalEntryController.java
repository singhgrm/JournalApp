package in.sp.main.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//in this we will save the data in db which we acess any time USING MONGODB
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
@RequestMapping("/journal")
//mongodb controller code
public class JournalEntryController {
    
//here for unique id we have journal details..we use mongodb to store data here we are not using response enetity
//in next controller we use responseEntity
	
@Autowired
Services myservice;
@PostMapping
public boolean createEntry(@RequestBody JournalEntry myEntry)
{
//the data which have been taken from postman in json formate that is stoted cutrently in @RequestBody JournalEntry myEntry in form of java object
return  myservice.CreateJournal(myEntry, null);
}

//giving data more than 2 userd..
@PostMapping("/bulk")
public boolean createEntries(@RequestBody List<JournalEntry> entries) {
    for (JournalEntry entry : entries) {
        myservice.CreateJournal(entry, null);
    }
    return true;
}
@GetMapping
public List<JournalEntry>  getAllRecord()
{
	return myservice.getAllJournal();
}

@GetMapping("/id/{myid}")
public JournalEntry GetJournalEntryById(@PathVariable String myid)
{
	return myservice.getJournalEntryById(myid).orElse(null);
}
//
@DeleteMapping("/id/{myid}")
public void DeleteJournalEntryById(@PathVariable String myid)
{
	 myservice.deleteJournalEntryById(myid);
}


@DeleteMapping("/id")
public void DeleteJournalEntryAll()
{
	 myservice.deleteJournalEntry();
}

@PutMapping("/{id}")
public JournalEntry UpdateUserDetails(@PathVariable String id , @RequestBody JournalEntry updated_journal) {
	JournalEntry old_journal= myservice.getJournalEntryById(id).orElse(null);
if(old_journal !=null)
	{
	
old_journal.setTitle(updated_journal.getTitle()!=null  ? updated_journal.getTitle() : old_journal.getTitle());
old_journal.setContent(updated_journal.getContent()!=null && updated_journal.getContent().equals("")? updated_journal.getContent() : old_journal.getContent());	
myservice.CreateJournal(old_journal, null);
return old_journal;
	}
	System.out.println("no data found for this id "+id);
	System.out.println(6/'0');
	return null;

}



}
