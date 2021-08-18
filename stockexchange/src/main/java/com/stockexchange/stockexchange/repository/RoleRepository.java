package com.stockexchange.stockexchange.repository;

import com.stockexchange.stockexchange.model.ERole;
import com.stockexchange.stockexchange.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}