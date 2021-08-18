package com.stockexchange.stockexchange.service;

import com.stockexchange.stockexchange.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {


    ResponseEntity<User> updateUser(Long id, User userDetails);

    ResponseEntity<Map<String, Boolean>> deleteUser(Long id);
    List<User> getAllUsers();
    Optional<User> selectById(Long id);


}
