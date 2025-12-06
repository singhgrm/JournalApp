package in.sp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EnggJounalAppApplication
{

public static void main(String[] args) 
	{
	
	System.out.println("current ruuning envoirment is ");
	ConfigurableApplicationContext context=	SpringApplication.run(EnggJounalAppApplication.class, args);
//with this we can get to know ,what is ruung currently production server pr development server
	//this will give all information like port active ,inactive many thins
//System.out.println(context.getEnvironment());
	//this will give just active profile
System.out.println("get active profile");
System.out.println(context.getEnvironment().getActiveProfiles()[0]);
System.out.println("hii garima hope you are doing well");
	
	}
		
  @Bean 
	public PlatformTransactionManager okay(MongoDatabaseFactory dbFactory)
	{
		return new MongoTransactionManager(dbFactory);
	}

}

