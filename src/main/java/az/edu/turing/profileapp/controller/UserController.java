package az.edu.turing.profileapp.controller;

import az.edu.turing.profileapp.dto.UserDto;
import az.edu.turing.profileapp.entity.UserEntity;
import az.edu.turing.profileapp.exception.UserNotFoundException;
import az.edu.turing.profileapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Get all USERS:\"GET -> /api/v1/users\"");
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id) throws UserNotFoundException {
        log.info("Get user by id:\"{}\"", id);
        return userService.findUserById(id);
    }

    @PostMapping
    public UserDto create_User(@RequestBody UserDto userDto) throws UserNotFoundException {
        log.info("Create user:\"{}\"", userDto);
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto update_User(@PathVariable int id, @RequestBody UserDto userDto) throws UserNotFoundException {
        log.info("Update user with id:\"{}\"", id);
        return userService.updateUser(id,userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) throws UserNotFoundException {
        log.info("Delete user by id:\"{}\"", id);
        userService.deletebyId(id);
    }


}