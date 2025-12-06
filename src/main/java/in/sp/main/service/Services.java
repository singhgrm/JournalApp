package in.sp.main.service;

import java.util.*;

import org.springframework.stereotype.Component;

import in.sp.main.entities.JournalEntry;
import in.sp.main.entities.User;
//here we will write business like like additon dleetion 
//using component it will create the bean for ioc container
public interface Services 

{
//define crud function..
public boolean CreateJournal(JournalEntry journal, String username);	
public boolean CreateJournal(JournalEntry journal);	
public	List<JournalEntry> getAllJournal();
public Optional<JournalEntry> getJournalEntryById(String id);
public JournalEntry updateJournalEntry(String id,JournalEntry journal);
public void deleteJournalEntry();
void deleteJournalEntryById(String id);
public boolean deleteJournalById(String id, String userName);
}
