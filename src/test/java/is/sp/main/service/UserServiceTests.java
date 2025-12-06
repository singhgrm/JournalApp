package is.sp.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.sp.main.EnggJounalAppApplication;
import in.sp.main.entities.User;
import in.sp.main.repository.UserRepository;
import in.sp.main.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EnggJounalAppApplication.class)
public class UserServiceTests {

	@Autowired
UserRepository userRepository;
	//we can write here multiple tests as methods
//assert me4ans promise krna
	
@Autowired
UserService userService;
	//beacuse  we have used @disabled with finction restfindbyusername() so now while running junit we can not test that
	@Disabled
	@Test 
	public void calculation()
	{
		assertEquals(4,8/2);
	}
//	 instead of this we can pass string beacuse all the username data type is string only
	 
	
//	@ParameterizedTest
//	@ValueSource(strings= {
//			"tanu",
//			"honey",
//			"new"
//	})
//	public void testFindByUserName(String name)
//	{
//		User user=userRepository.findByUsername(name);
//		assertTrue(user.getJournalEntries().size()>=1,"failed for "+name);
//		assertEquals(4,2+2);
//		//username information is comnig from database
//	//	assertNotNull(userRepository.findByUsername("garima"));
//	    assertTrue(5>3);
//	}
	
	
@Disabled
	@ParameterizedTest
	@CsvSource({
		"honey",
		"tanu",
		"new"
	})	
	public void testFindByUserName(String name) {
	    try {
	        User user = userRepository.findByUsername(name);
	        if (user == null) {
	            System.out.println("User not found: " + name);
	            assertNull(user);
	            return;
	        }
	        assertTrue(user.getJournalEntries().size() >= 1, "failed for " + name);
	    } catch (Exception ex) {
	        ex.printStackTrace(); // this will show the exact MappingException message and which property is missing
	        fail("Mapping exception while reading user: " + ex.getMessage());
	    }
	}


	
	

	@Disabled
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	public void testSaveNewUser(User user)
	{
		assertTrue(userService.saveNewUser1(user));

	}
	
	
@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"3,5,8",
		"5,7,12"
	})
	public void test(int a,int b,int expected)
	{
		assertEquals(expected,a+b);
	}

}
