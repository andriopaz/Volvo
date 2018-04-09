package com.volvo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.volvo.test.domain.Permission;
import com.volvo.test.service.PermissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionRestTest {

	@Autowired
	PermissionService permissionService;
	
	@Test
	public void getAllPermissions() {
		Permission d1 = new Permission(null, "Permission 20", "desc1", null);
		Permission d2 = new Permission(null, "Permission 40", "desc2", null);
		permissionService.save(d1);
		permissionService.save(d2);
		
		assertThat(permissionService.findAll()).anyMatch(d -> d.getName().equalsIgnoreCase(d1.getName()));
		assertThat(permissionService.findAll()).anyMatch(d -> d.getName().equalsIgnoreCase(d2.getName()));
	}
	
	@Test
	public void getPermissionById() {
		assertThat(permissionService.findOne(1l).isPresent());
	}
}
