package in.sp.main.repository;
import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
//repositoty take entity and id type 

import in.sp.main.entities.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,String>
{
     
}


