package in.sp.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.sp.main.entities.JournalEntry;
import in.sp.main.entities.User;
import in.sp.main.repository.JournalEntryRepository;

//here we will write business like like additon deletion
@Service
@Component
public class ServiceImpl  implements Services{
	
     @Autowired 
JournalEntryRepository journalRepository;
     
 @Autowired
UserService userService;
 
private static  final Logger log=LoggerFactory.getLogger(ServiceImpl.class);
 
@Override 
//here we will save the record on uiser and journal entry both
//tranction annotation use to follow atomicity property,means either all or none
@Transactional
public boolean CreateJournal(JournalEntry journal ,String user_name)
	{
		//first find user
		
		try
		{	
User user=userService.findDeatailsByUserName(user_name);
journal.setDate(LocalDateTime.now());
//find saved journal entries	
JournalEntry saved=	journalRepository.save(journal);
//now add this saved journal into user journal entries
	user.getJournalEntries().add(saved);
//	user.setUsername(null);
	//now agaion save userr into userrservice
	userService.SaveUser(user);
log.info("user and journal both are save in journal  ", user);
log.info("user saved in logs");
		return true;
		
	}
		
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("error comes in log");
			throw new RuntimeException("an error occour which saving the journal entry for user  =   "+e);
		}
		
	}
	
	@Override
	public List<JournalEntry> getAllJournal() 
	{
     // TODO Auto-generated method stub
	return  	journalRepository.findAll();
	}

	@Override
	public JournalEntry  updateJournalEntry(String id, JournalEntry journal) {
		// TODO Auto-generated method stub
		JournalEntry old_journal=	journalRepository.findById(id).orElse(null);
		if(old_journal!= null)
		{
			
	//save function is use for update
		return 	journalRepository.save(journal);
		}
		
	throw new RuntimeException("-------user not present with this id--------"+id);
	}

	@Override
	public void deleteJournalEntry() {
		// TODO Auto-generated method stub
		journalRepository.deleteAll();	
	}
	
	public void  deleteJournalEntryById(String id) {
		journalRepository.deleteById(id);
		
	}
	
	@Override
	public Optional<JournalEntry> getJournalEntryById(String id) {
		// TODO Auto-generated method stub
	return journalRepository.findById(id);
	}

	//here we will remove thedeatils from user journal entries and journal entries both=
	@Transactional
	@Override
	public boolean deleteJournalById(String id ,String username)  {
		// TODO Auto-generated method stub 
		boolean removed=false;
		try
		{
		 User user=userService.findDeatailsByUserName(username);
	removed=	 user.getJournalEntries().removeIf(x-> x.getId().equals(id));
		 if(removed)
		 {
			 userService.SaveUser(user);
			 
			 //now journal id will be removed from both user journal entries and jornal itself
			 journalRepository.deleteById(id);
		 }
		 
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			log.info("an error occur while deleting the entry ,eans id = "+id+"does not exist");
			
		}
		return removed;
		 }

	@Override
	public boolean CreateJournal(JournalEntry journal)
	{
		// TODO Auto-generated method stub
		journalRepository.save(journal);
		return true;
	}
	
	public User findByUserName(String user_name)
	{
		return userService.findDeatailsByUserName(user_name);
	
		
	}

}
