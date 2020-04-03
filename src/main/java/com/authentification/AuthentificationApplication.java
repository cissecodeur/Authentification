package com.authentification;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.authentification.dao.TaskRepository;
import com.authentification.entity.AppRole;
import com.authentification.entity.AppUser;
import com.authentification.entity.Task;
import com.authentification.service.AccountService;

@SpringBootApplication
public class AuthentificationApplication implements CommandLineRunner{

	 @Autowired
	 private TaskRepository taskRepository;
	 
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 
	 
	 
	 @Autowired
	 private AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
	}

	@Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
       return new BCryptPasswordEncoder();
    }
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		accountService.saveUser(new AppUser(null,"admin","1234",null));
		accountService.saveUser(new AppUser(null,"user","12345",null));
		
		accountService.saveRole(new AppRole(null,"ADMIN"));
		accountService.saveRole(new AppRole(null,"USER"));
		
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		
		Stream.of("T1","T2","T3").forEach(t->{
			taskRepository.save(new Task(null, t));
		});
		taskRepository.findAll().forEach(x->{
			System.out.println(x.getTaskName());
		});
		
		
//		
//		String password = "1234";
//		String encodedPassword = passwordEncoder.encode(password);
//		
//
//		System.out.printf("Password is         : " + password);
//		System.out.println("Encoded Password is : " + encodedPassword);
//		
//
//		
//		boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
//		System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

		
	}
	
    
	
}
