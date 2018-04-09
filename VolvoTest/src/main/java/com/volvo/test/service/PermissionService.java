package com.volvo.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volvo.test.domain.Permission;
import com.volvo.test.persistence.PermissionRepository;

@Service
public class PermissionService {
	
	@Autowired
	private PermissionRepository repository;
	
	public void save(Permission permission) {
		repository.save(permission);
	}
	
	public void save(Iterable<Permission> permissions) {
		repository.saveAll(permissions);
	}
	
	public List<Permission> findAll() {
		return repository.findAll();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Optional<Permission> findOne(Long id) {
		return repository.findById(id);
	}
}
