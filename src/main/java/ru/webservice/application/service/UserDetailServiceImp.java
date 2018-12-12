package ru.webservice.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webservice.application.domain.Role;
import ru.webservice.application.domain.User;
import ru.webservice.application.repositories.UserRepo;

import java.util.stream.Collectors;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserDetailServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                                    .map(Role::getName)
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toSet())
        );
    }
}
