package in.sp.main.repository;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
//repositoty take entity and id type 
import org.springframework.stereotype.Repository;

import in.sp.main.entities.JournalEntry;
import in.sp.main.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User,ObjectId>
{
	
  User findByUsername(String username);

  void deleteByUsername(String username);
}


