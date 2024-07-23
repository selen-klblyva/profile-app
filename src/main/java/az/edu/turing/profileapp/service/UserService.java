package az.edu.turing.profileapp.service;

import az.edu.turing.profileapp.dto.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUser();

    UserDto createUser(UserDto userDto);

    public UserDto findUserById(int id);

    public int deletebyId(int id);

    UserDto updateUser(int id,UserDto userDto);


}
