package in.sp.main.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import java.util.*;
@Document(collection="users")
@Data
public class User 
{
	public ObjectId getId() 
	{
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public List<JournalEntry> getJournalEntries() {
		return journalEntries;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setJournalEntries(List<JournalEntry> journalEntries) {
		this.journalEntries = journalEntries;
	}
	@Id
private ObjectId id;
	//all the user name should be ubique thats why we are using indexing and we will login with username and opassword
//spring boot does not support indexing automatically so we need to add configuration in  application.propertoes to use spring

	@Indexed(unique=true)
	@NonNull
	//this nonnull we are using with lombok
 private String username;
	@NonNull
 private String password;	
	@DBRef(lazy = true)
    @JsonIgnoreProperties("user")
private List<JournalEntry>journalEntries=new ArrayList<>();
//using DBRef we are creating refernce of JornalEntries in usrs table it will craetev like a foreign and have reference/address using id 
	//role can be anything like- user,admin,developer 
	private List<String>roles;
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public User()
	{
		
	}
	public User(String name, String pass)
	{
		this.username=name;
		this.password=pass;
	}

}
