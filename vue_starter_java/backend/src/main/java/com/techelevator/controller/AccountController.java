package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

/**
 * AccountController
 */
@RestController
public class AccountController {
     
    @Autowired
    private AuthProvider auth;

    @Autowired
    private JwtTokenHandler tokenHandler;
    
	private final UserDao userDao;

	public AccountController(UserDao userDao) {
		this.userDao = userDao;
	}


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
        if (auth.signIn(user.getUsername(), user.getPassword())) {
            User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(user.getUsername(), currentUser.getRole());
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        } else {
            auth.register(user.getUsername(), user.getPassword(), user.getRole());
        }
        
        return "{\"success\":true}";
    }
    
    @RequestMapping(path = "/api/reset", method = RequestMethod.POST)
    public String resetPassWord(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        } else {
            auth.register(user.getUsername(), user.getPassword(), user.getRole());
        }
        
        return "{\"success\":true}";
    }

	@GetMapping(path="/api/user")
	public User getUserDetails() {
		User currentUser = auth.getCurrentUser();
		return userDao.getUserById(currentUser.getId());
	}
	
	@GetMapping(path="/api/admin/checkin/{eventId}")
	public Boolean getUserCheckin(@PathVariable long eventId) {
	List<User> adminUsers = userDao.getAdminIds();
	for(User admin : adminUsers) {
		if(userDao.isUserCheckedIn(eventId, admin.getId())) {
			return true;
		}
	}
	return false;
	}
	
	
    
}