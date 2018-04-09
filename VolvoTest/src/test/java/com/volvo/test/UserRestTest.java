package com.volvo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.volvo.test.domain.User;
import com.volvo.test.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestTest {

	@Autowired
	UserService userService;
	
	@Test
	public void getAllUsers() {
		User d1 = new User(null, "User 20", "desc1", null, null);
		User d2 = new User(null, "User 40", "desc2", null, null);
		userService.save(d1);
		userService.save(d2);
		
		assertThat(userService.findAll()).anyMatch(d -> d.getName().equalsIgnoreCase(d1.getName()));
		assertThat(userService.findAll()).anyMatch(d -> d.getName().equalsIgnoreCase(d2.getName()));
	}
	
	@Test
	public void getUserById() {
		assertThat(userService.findOne(1l).isPresent());
	}
}
