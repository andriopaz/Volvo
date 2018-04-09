package com.volvo.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volvo.test.domain.User;
import com.volvo.test.persistence.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public void delete(Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			user.get().setPermissions(null);
			user.get().setDepartment(null);
			repository.save(user.get());
			repository.deleteById(id);
		}
	}
	
	public Optional<User> findOne(Long id) {
		return repository.findById(id);
	}
}
