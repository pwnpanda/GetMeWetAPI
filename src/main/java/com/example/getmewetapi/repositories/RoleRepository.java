package com.example.getmewetapi.repositories;

import com.example.getmewetapi.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
