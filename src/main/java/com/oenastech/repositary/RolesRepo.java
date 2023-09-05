package com.oenastech.repositary;

import com.oenastech.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RolesRepo extends JpaRepository<Role,Long> {
    Role getRoleByName(String name);

}
