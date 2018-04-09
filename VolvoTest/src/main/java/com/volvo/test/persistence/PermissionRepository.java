package com.volvo.test.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volvo.test.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}