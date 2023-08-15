package com.programming.weatherserver.controller;



import com.programming.weatherserver.validations.ResponseErrorValidation;
import com.programming.weatherserver.dto.UserDto;
import com.programming.weatherserver.facade.UserFacade;
import com.programming.weatherserver.model.User;
import com.programming.weatherserver.payload.MessageResponse;
import com.programming.weatherserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {



    private final UserService userService;

    private final UserFacade userFacade;

    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public UserController(UserService userService, UserFacade userFacade, ResponseErrorValidation responseErrorValidation) {
        this.userService = userService;
        this.userFacade = userFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal){

        User user = userService.getCurrentUser(principal);
        UserDto userDTO = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @GetMapping("/allUser")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDTO = userService.getAllUser().stream().map(userFacade::userToUserDTO).collect(Collectors.toList());
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable("username") String username ){
        User user = userService.getUserByUsername(username);
        UserDto userDTO = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId ){
        User user = userService.getUserById(userId);
        UserDto userDTO = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDTO, BindingResult bindingResult, Principal principal){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateUser(userDTO,principal);
        UserDto userUpdated = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }



}


