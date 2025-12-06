package is.sp.main.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import in.sp.main.entities.User;

public class UserArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		//this password and username will be save into datavase as a user record
//		return Stream.of(
//				Arguments.of(User.builder().username("ram").password("ram").build()),
//				Arguments.of(User.builder().username("shyam").password("shyam").build())
//				
//				);
		
		User u1 = new User("anuj", "anuj");
        User u2 = new User("hannu", "hannu");

        return Stream.of(
            Arguments.of(u1),
            Arguments.of(u2)
        );
				
	}

}
