package com.programming.weatherserver.facade;


import com.programming.weatherserver.dto.UserDto;
import com.programming.weatherserver.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDto userToUserDTO(User user){
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatarId(user.getAvatarId());

        return userDTO;
    }
}
