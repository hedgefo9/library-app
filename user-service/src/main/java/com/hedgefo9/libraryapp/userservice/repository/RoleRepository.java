package com.hedgefo9.libraryapp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hedgefo9.libraryapp.userservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
