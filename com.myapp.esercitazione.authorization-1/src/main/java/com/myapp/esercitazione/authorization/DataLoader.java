package com.myapp.esercitazione.authorization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.myapp.esercitazione.authorization.model.UserLog;
import com.myapp.esercitazione.authorization.repository.UserLogRepository;

/*
 * Classe di debug per la gestione del DB.
 */

@Component
public class DataLoader implements ApplicationRunner {

    private UserLogRepository userRepository;

    @Autowired
    public DataLoader(UserLogRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(ApplicationArguments args) {
    	/*  
    	 * Un nuovo user può essere inserito con una lista di vuota di post o semplicemnte con un NULL.
    	 */
    	UserLog user = new UserLog();
    	user.setPassword("password");
    	user.setUsername("username");
    	//user.se
    	userRepository.save(user);    	
    }  
    
   
    
}

	
	
