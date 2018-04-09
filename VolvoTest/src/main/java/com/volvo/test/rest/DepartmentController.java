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

import com.volvo.test.domain.Department;
import com.volvo.test.service.DepartmentService;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/service/departments")
    public @ResponseBody
    Iterable<Department> getAllDepartments() {
        return this.departmentService.findAll();
    }
    
    @RequestMapping(value="/service/department/{id}", method=RequestMethod.GET)
    public @ResponseBody Department getDepartmentById(@PathVariable Long id) {
        return this.departmentService.findOne(id).get();
    }
    
    @RequestMapping(value="/service/department/delete/{id}", method=RequestMethod.GET)
    public @ResponseBody void deleteDepartmentById(@PathVariable Long id) {
    	this.departmentService.delete(id);
    }
    
    @RequestMapping(value="/saveDepartment", method = RequestMethod.POST)
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
    	
    	if (department == null || department.getId() == null || StringUtils.isEmpty(department.getName())) {
    		return new ResponseEntity<Department>(department, HttpStatus.BAD_REQUEST);
    	}
    	
    	this.departmentService.save(department);
    	
    	return new ResponseEntity<Department>(department, HttpStatus.OK);
    }
}
