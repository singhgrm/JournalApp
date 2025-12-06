package in.sp.main.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import in.sp.main.entities.JournalEntry;
import in.sp.main.entities.User;


public interface UserService  
{
	public boolean SaveUser(User user);	
	public void saveNewUser(User user);
public	boolean saveNewUser1(User user);
	public void 	saveAdmin(User user);
	public	List<User> getAllUser();
	public User getUserByUsername(String name);
	public User updateUserEntry(ObjectId id,User user);
	public void deleteUserEntry();
	void deleteUserEntryByName(String name);
	public User findDeatailsByUserName(String name);
}
