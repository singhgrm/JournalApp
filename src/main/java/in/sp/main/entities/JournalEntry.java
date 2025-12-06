package in.sp.main.entities;


import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
@Data
@NoArgsConstructor
@Document(collection="journal_entries")
public class JournalEntry 
{

public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public LocalDateTime getCurr_date() {
		return curr_date;
	}
	public User getUser() {
		return user;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}
	public void setCurr_date(LocalDateTime curr_date) {
		this.curr_date = curr_date;
	}
	public void setUser(User user) {
		this.user = user;
	}
@Id

private String id;
@NonNull
private String title;
private String content;
private LocalDateTime date;
LocalDateTime curr_date;
@DBRef(lazy = true)
@JsonIgnoreProperties("journalEntries")
private User user;

//public void setDate(LocalDateTime now) {
//	// TODO Auto-generated method stub
//	this.curr_date=now;
//}

}
