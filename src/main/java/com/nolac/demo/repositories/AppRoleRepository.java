package com.nolac.demo.repositories;

import com.nolac.demo.model.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {
    AppRole findByRole(String role);
}
