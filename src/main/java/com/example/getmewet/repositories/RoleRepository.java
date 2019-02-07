package com.example.getmewet.repositories;

import com.example.getmewet.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
