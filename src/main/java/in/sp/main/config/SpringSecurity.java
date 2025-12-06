package in.sp.main.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.sp.main.service.UserDetailServiceImpl;
@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user").authenticated()

                        .requestMatchers("/user_with_journal/**").authenticated()

                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

//    

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
   // @Bean
//  public AuthenticationManager authenticationManager() {
//      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//      provider.setUserDetailsService(userDetailsService);
//      provider.setPasswordEncoder(passwordEncoder());
//      return new ProviderManager(provider);
//  }
}



//public class SpringSecurity {
//	 @Autowired
//	    private UserDetailServiceImpl userDetailsService;
//
//	 @Bean
//	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	     return http
//	             .csrf(AbstractHttpConfigurer::disable)
//	             .sessionManagement(session -> 
//	                     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	             )
//	             .authorizeHttpRequests(auth -> auth
//	                     .requestMatchers(HttpMethod.POST, "/user").permitAll()
//	                     .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
//	                     .requestMatchers(HttpMethod.PUT, "/user").authenticated()
//	                     .requestMatchers("/user_with_journal/**","/user/**").authenticated()
//	                     .requestMatchers("/public/**").permitAll()
//	                     .anyRequest().permitAll()
//	             )
//	             .httpBasic(Customizer.withDefaults())
//	             .build();
//	 }
//
//	    @Bean
//	    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
//	        
//	        provider.setPasswordEncoder(passwordEncoder());
//	        return new ProviderManager(provider);
//	    }
//
//	    
//	   
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	 
//	 
//	 
//	 
//	 
//	    // ✅ Modern Security configuration
////	 @Bean
////	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////		    return http
////		    		
////		            .csrf(AbstractHttpConfigurer::disable)
////		            .authorizeHttpRequests(auth -> auth
////		                    .requestMatchers(HttpMethod.POST, "/user").permitAll()
////		                    .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
////		                    .requestMatchers(HttpMethod.PUT, "/user").authenticated()
////		                    .requestMatchers("/user_with_journal/**").authenticated()
////		                    .requestMatchers("/public/**").permitAll()
////		                    .anyRequest().permitAll()
////		            )
////		            .httpBasic(Customizer.withDefaults())
////		            .build();
////		}
//
//	 
////	 @Bean
////	 public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
////	     return http
////	             .csrf(AbstractHttpConfigurer::disable)
////	             .authorizeHttpRequests(auth -> auth
////	                     .requestMatchers("/user/**").permitAll()                  // open (signup, get all users)
////	                     .requestMatchers("/user_with_journal/**" , "/user/**").authenticated() // protected
////	                     .anyRequest().permitAll()
////	             )
////	             .httpBasic(Customizer.withDefaults())
////	             .build();
////	 }
//
//	    
////this is configure method...
//	    
//
//	
//}
