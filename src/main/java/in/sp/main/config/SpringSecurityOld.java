//package in.sp.main.config;
//
//import java.beans.Customizer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import in.sp.main.service.UserDetailServiceImpl;
//import in.sp.main.service.UserServiceImpl;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity {
//   
//	 @Autowired
//	    private UserDetailServiceImpl userDetailsService;
//
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
//
//	      return http.authorizeRequests()
//	                        .antMatchers("/user_with_journal/**").authenticated()
//	                        .anyRequest().permitAll()
//	                       .and()
//	                .httpBasic();
//	                
//	    }
//	    
//
//	    @Autowired
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//
//	    }
////
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	    
//
//	
//}


