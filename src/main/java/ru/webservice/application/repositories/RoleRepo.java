package ru.webservice.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webservice.application.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
    Role findByName(String name);
}
