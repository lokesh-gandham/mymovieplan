package com.nmscinemas.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.nmscinemas.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
