package com.worldnavigator.webapp.controllers;

import com.worldnavigator.webapp.dto.UserDto;
import com.worldnavigator.webapp.entities.User;
import com.worldnavigator.webapp.exceptions.UserNotFoundException;
import com.worldnavigator.webapp.repositories.UserRepository;
import com.worldnavigator.webapp.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody UserDto account) {
        return userService.create(account);
    }

    @GetMapping("@{username}")
    public User retrieve(@PathVariable String username) {
        return userRepository
                .findById(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("There is no account for %s!", username)));
    }

    @DeleteMapping("@{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username) {
        userRepository.deleteById(username);
    }

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("me")
    public User me(User user) {
        return user;
    }
}
