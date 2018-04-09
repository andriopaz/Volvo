package com.volvo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.volvo.test.domain.Department;
import com.volvo.test.service.DepartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRestTest {

	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void getAllDepartments() {
		Department d1 = new Department(null, "Department 20", "desc1", null);
		Department d2 = new Department(null, "Department 40", "desc2", null);
		departmentService.save(d1);
		departmentService.save(d2);
		
		assertThat(departmentService.findAll()).anyMatch(d -> d.getName().equalsIgnoreCase(d1.getName()));
		assertThat(departmentService.findAll()).anyMatch(d -> d.getName().equalsIgnoreCase(d2.getName()));
	}
	
	@Test
	public void getDepartmentById() {
		assertThat(departmentService.findOne(1l).isPresent());
	}
}
