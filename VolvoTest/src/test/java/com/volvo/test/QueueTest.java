package com.volvo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.volvo.test.domain.Department;
import com.volvo.test.domain.Permission;
import com.volvo.test.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueTest {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testPermissionReceiver() {
		Permission permission = new Permission();
		permission.setId(50l);
		permission.setName("Permission 50");
		permission.setDescription("Permission Description 50");
		
		this.jmsTemplate.convertAndSend("permission", permission);
		this.jmsTemplate.setReceiveTimeout(10000);
		assertThat(this.jmsTemplate.receiveAndConvert("permissionBack")).isEqualTo(permission.getName());
	}
	
	@Test
	public void testDepartmentReceiver() {
		Department department = new Department();
		department.setId(50l);
		department.setName("Department 8");
		department.setDescription("Department Description 8");
		
		this.jmsTemplate.convertAndSend("department", department);
		this.jmsTemplate.setReceiveTimeout(10000);
		assertThat(this.jmsTemplate.receiveAndConvert("departmentBack")).isEqualTo(department.getName());
	}
	
	@Test
	public void testUserReceiver() {
		User user = new User();
		user.setId(50l);
		user.setName("User 32");
		user.setDescription("User Description 32");
		
		this.jmsTemplate.convertAndSend("user", user);
		this.jmsTemplate.setReceiveTimeout(10000);
		assertThat(this.jmsTemplate.receiveAndConvert("userBack")).isEqualTo(user.getName());
	}
}
