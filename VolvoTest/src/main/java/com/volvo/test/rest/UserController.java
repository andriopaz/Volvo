package com.volvo.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.volvo.test.domain.User;
import com.volvo.test.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/service/users")
    public @ResponseBody
    Iterable<User> getAllUserss() {
        return this.userService.findAll();
    }
    
    @RequestMapping(value="/service/user/{id}", method=RequestMethod.GET)
    public @ResponseBody User getUserById(@PathVariable Long id) {
    	return this.userService.findOne(id).get();
    }
    
    @RequestMapping(value="/service/user/delete/{id}", method=RequestMethod.GET)
    public @ResponseBody void deleteUserById(@PathVariable Long id) {
    	this.userService.delete(id);
    }
    
    @RequestMapping(value="/saveUser", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
    	
    	if (user == null || user.getId() == null || StringUtils.isEmpty(user.getName())) {
    		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
    	}
    	
    	this.userService.save(user);
    	
    	return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
