package com.example.userservice.web.controller;

import com.example.userservice.data.entity.User;
import com.example.userservice.service.user.UserService;
import com.example.userservice.web.model.UserReqModel;
import com.example.userservice.web.model.UserSimpleRespModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."User with id \{id} not found");
        }
    }

    @GetMapping
    ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/by-county")
    ResponseEntity<List<UserSimpleRespModel>> findAllByCountry(@RequestParam String countryTerm) {
        return ResponseEntity.ok(userService.findByCountry(countryTerm));
    }

    @GetMapping("/by-name")
    ResponseEntity<List<UserSimpleRespModel>> findAllByName(@RequestParam String userNameTerm) {
        return ResponseEntity.ok(userService.findByName(userNameTerm));
    }

    @PostMapping
    public ResponseEntity<UserSimpleRespModel> createUser(@RequestBody UserReqModel user) {
        try {
            UserSimpleRespModel createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
