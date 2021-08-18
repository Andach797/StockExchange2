package com.stockexchange.stockexchange.service.impl;

import com.stockexchange.stockexchange.exception.ResourceNotFoundException;
import com.stockexchange.stockexchange.model.ERole;
import com.stockexchange.stockexchange.model.Role;
import com.stockexchange.stockexchange.model.Stock;
import com.stockexchange.stockexchange.model.User;
import com.stockexchange.stockexchange.repository.RoleRepository;
import com.stockexchange.stockexchange.repository.UsersRepository;
import com.stockexchange.stockexchange.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    UsersRepository usersRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, User userDetails) {
        try {
            User user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found" + id));
            user.setUserName(userDetails.getUserName());
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setSurname(userDetails.getSurname());
            User updatedUser = usersRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id) {
        try {
            User user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found" + id));
            usersRepository.delete(user);
            Map<String, Boolean> res = new HashMap<>();
            res.put("Deleted", Boolean.TRUE);
          return   ResponseEntity.ok(res);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    @Override
    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }
    @Override
    public Optional<User> selectById(Long id){
        return usersRepository.findById(id);
    }



    }
