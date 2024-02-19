package com.example.securityproject.repositories;

import com.example.securityproject.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
