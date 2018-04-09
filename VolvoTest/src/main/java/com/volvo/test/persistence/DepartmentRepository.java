package com.volvo.test.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volvo.test.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
