package com.stockexchange.stockexchange.security.jwt;

import com.stockexchange.stockexchange.model.ERole;
import com.stockexchange.stockexchange.model.Role;
import com.stockexchange.stockexchange.model.User;
import com.stockexchange.stockexchange.repository.RoleRepository;
import com.stockexchange.stockexchange.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;





    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        if(usersRepository.existsByUserName("Admin"))
            return;
        else {
            User user = new User();
            user.setUserName("Admin");
            user.setName("admin");
            user.setSurname("admin");
            user.setPassword("123456");
            user.setEmail("admin@gmail.com");
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            roles.add(userRole);
            user.setRoles(roles);

            usersRepository.save(user);

            alreadySetup = true;
        }
    }



    @Transactional
    Role createRoleIfNotFound(
            String name) {

        Role role = roleRepository.findByName(ERole.valueOf(name));
        if (role == null) {
            role = new Role(ERole.valueOf(name));
            roleRepository.save(role);
        }
        return role;
}}