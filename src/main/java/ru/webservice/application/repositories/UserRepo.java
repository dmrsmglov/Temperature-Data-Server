package ru.webservice.application.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.webservice.application.domain.User;

public interface UserRepo extends CrudRepository<User, Long> {

}
