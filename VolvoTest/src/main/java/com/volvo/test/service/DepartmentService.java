package com.volvo.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volvo.test.domain.Department;
import com.volvo.test.persistence.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	public Department save(Department department) {
		return repository.save(department);
	}
	
	public List<Department> findAll() {
		return repository.findAll();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Optional<Department> findOne(Long id) {
		return repository.findById(id);
	}
}
