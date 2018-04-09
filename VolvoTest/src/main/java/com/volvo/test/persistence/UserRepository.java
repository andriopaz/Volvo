package com.volvo.test.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volvo.test.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
