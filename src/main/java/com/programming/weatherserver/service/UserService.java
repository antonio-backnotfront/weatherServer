package com.programming.weatherserver.service;


import com.programming.weatherserver.exeptions.OldPasswordIsIncorectException;
import com.programming.weatherserver.exeptions.UserExistException;
import com.programming.weatherserver.repository.UserRepository;
import com.programming.weatherserver.dto.UserDto;
import com.programming.weatherserver.enums.ERole;
import com.programming.weatherserver.model.User;
import com.programming.weatherserver.request.SignupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAllByOrderByCreatedDateDesc();
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setUsername(userIn.getUsername());
        user.setAvatarId(1);
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.USER);
        try {
            LOG.info("Saving User {}" + userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {

            LOG.error("Error during registration. {}" + e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }


    }


    public User updateUser(UserDto userDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setUsername(userDTO.getUsername());
        user.setAvatarId(userDTO.getAvatarId());
        return userRepository.save(user);

    }

    public User getCurrentUser(Principal principal) {

        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        User user =  userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
        System.out.println(user.getAuthorities());
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }


}
