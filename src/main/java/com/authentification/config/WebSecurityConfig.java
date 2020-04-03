package com.authentification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserDetailsService userDetailsService ;
	
	@Autowired
	private  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	
	// Cette classe montrera a springSecurity comment il ira chercher les utilisateurs et les passwords
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
    
    
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   http.csrf().disable();
	   http.formLogin();
	   http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();  //permettre l'acces a ses pages
	   http.authorizeRequests().antMatchers(HttpMethod.POST,"task/**").access("hasAnyRole(\"ADMIN\")"); // Seul un Admin peut faire un POST
	   http.authorizeRequests().anyRequest().authenticated();
	   
   }
//		
////		//Page ne necessitant pas d'authentificaauth.app_usertion
//		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
//		
//		// /userInfo page neccessite d'etre login comme ROLE_USER or ROLE_ADMIN.
//        // If no login, it will redirect to /login page.
//		http.authorizeRequests().antMatchers("/userInfos").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
//	
//		// Admin seulement
//	    http.authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')");
//	
//	    
//	     //Pour les pages neccessitant des privileges generer une AccessDeniedExeption
//	    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//	
//	   //Config pour les formulaires 
//	    
//	    // Envoi de l'Url
//	    http.authorizeRequests().and().formLogin().loginProcessingUrl("");
//	    
//	    //
//	    http.authorizeRequests().and().formLogin().loginPage("");
//	    
//	    http.authorizeRequests().and().formLogin().defaultSuccessUrl("/userAccountInfo");
//	    http.authorizeRequests().and().formLogin().failureUrl("/login?error=true");
//	    http.authorizeRequests().and().formLogin().usernameParameter("username");//
//        http.authorizeRequests().and().formLogin().passwordParameter("password");
//        
//        //Config pour la page de deconnexion
//        http.authorizeRequests().and().formLogin().and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
//        
//        //Config pour Remember Me 
//        http.authorizeRequests().and().rememberMe().tokenRepository(this.persistentTokenRepository());
//        http.authorizeRequests().and().rememberMe().tokenValiditySeconds(1*24*60*60); //24h
        
	
	
	
	
	
}
