package ru.webservice.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webservice.application.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
