package com.myapp.esercitazione.authorization.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.myapp.esercitazione.authorization.authentication.UserAuthenticationService;
import com.myapp.esercitazione.authorization.model.UserLog;

@Service
public class UUIDAuthenticationService implements UserAuthenticationService {
    @Autowired
    private UserLogService userService;

    @Override
    public String login(String username, String password) {     	
	     Optional<UserLog> ret = userService.getByUsername(username);
	     if(ret.isPresent()) {
	    	 UserLog user = ret.get();
	    	 if(user.getPassword().equals(password)) {
	    		 user.setToken(UUID.randomUUID().toString());
                 userService.save(user);
	    		 return user.getToken();
	    	 }
	     }
	     return "";
    }

    @Override
    public UserLog authenticateByToken(String token) {    	
    	Optional<UserLog> ret = userService.getByToken(token);
    	if(!ret.isPresent()) {
    		return null;
    	}
        return ret.get();
    }

    @Override
    public void logout(String username) {
        userService.getByUsername(username)
                .ifPresent(u -> {
                    u.setToken(null);
                    userService.save(u);
                });
    }
}
