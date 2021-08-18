package com.stockexchange.stockexchange.controller;

import com.stockexchange.stockexchange.dto.StockDto;
import com.stockexchange.stockexchange.dto.UserDto;
import com.stockexchange.stockexchange.model.ERole;
import com.stockexchange.stockexchange.model.Role;
import com.stockexchange.stockexchange.model.Stock;
import com.stockexchange.stockexchange.model.User;
import com.stockexchange.stockexchange.repository.RoleRepository;
import com.stockexchange.stockexchange.repository.UsersRepository;
import com.stockexchange.stockexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/api/v1/")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }



    @GetMapping("/users/{id}")
    public Optional<User> selectById(@PathVariable Long id){
        return userService.selectById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
     return userService.updateUser(id,userDetails);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        return  userService.deleteUser(id);

    }

    @PostMapping("/users")
    public User createUsers (@RequestBody User user) throws Exception {
        if (usersRepository.existsByUserName(user.getUserName()) || usersRepository.existsByEmail(user.getEmail())) {
            throw new Exception("User with these credentials already exists");
        } else {
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole);
            user.setRoles(roles);
            return usersRepository.save(user);
        }
    }
    @PostMapping("/usersadmin")
    public User createUsersAdmin(@RequestBody User user){
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        roles.add(userRole);
        user.setRoles(roles);
        return usersRepository.save(user);
    }

}
