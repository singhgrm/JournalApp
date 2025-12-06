//package in.sp.main.controller;
////here we will save the data in map which we can use ony in map
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import in.sp.main.entities.JournalEntry;
//
//import java.util.*;
//@RestController
//@RequestMapping("/journal")
//public class JournalController {
//    
////here for unique id we have journal details..
//private Map<String , JournalEntry>journalEntries=new HashMap<>();
//
//@GetMapping
//public List<JournalEntry>  getAll()
//{
//	return new ArrayList<>(journalEntries.values());
//}
//
//	
//@PostMapping
//public boolean createEntry(@RequestBody JournalEntry myEntry)
//{
//journalEntries.put(myEntry.getId(), myEntry);
//return true;
//}
//
//@GetMapping("/id/{myid}")
//public JournalEntry getJournalEntryById(@PathVariable Long myid)
//{
//	return  journalEntries.get(myid);
//}
//
//@DeleteMapping("/id/{myid}")
//public JournalEntry deleteJournalEntryById(@PathVariable Long myid)
//{
//	return  journalEntries.remove(myid);
//}
//
////function to upadte recr=ords
//@PutMapping("/id/{myid}")
//public JournalEntry updateJournalEntryById(@PathVariable String myid,@RequestBody JournalEntry myEntry)
//{
//	return  journalEntries.put(myid,myEntry);
//}
//
//
//}
