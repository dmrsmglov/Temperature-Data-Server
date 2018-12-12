package ru.webservice.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import ru.webservice.application.domain.Role;
import ru.webservice.application.repositories.RoleRepo;

@Configuration
public class RoleConfig {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleConfig(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Value("${roles.authenticated.user}")
    private String user;

    @Value("${roles.authenticated.sensor}")
    private String sensor;

    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        createRoles();
    }

    private void createRoles() {
        if (!roleRepo.existsByName(user)) {
            roleRepo.save(new Role(user));
        }
        if (!roleRepo.existsByName(sensor)) {
            roleRepo.save(new Role(sensor));
        }
    }
}
