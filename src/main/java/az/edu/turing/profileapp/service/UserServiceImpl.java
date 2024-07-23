package az.edu.turing.profileapp.service;

import az.edu.turing.profileapp.dto.UserDto;
import az.edu.turing.profileapp.entity.UserEntity;
import az.edu.turing.profileapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(),
                        user.getUsername(),user.getAge(),
                        user.getCreatedAt(),user.getUpdatedAt(), user.getPhotoURL()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
           UserEntity userEntity = new UserEntity(userDto.getId(),userDto.getUsername(),userDto.getAge(),
                   userDto.getCreatedAt(),userDto.getUpdatedAt(), userDto.getPhotoURL());
           userRepository.create_User(userEntity);

        return userDto;
    }

    @Override
    public UserDto findUserById(int id) {
        UserEntity userEntity= userRepository.findById(id);
        if(userEntity==null){
           log.error("User not found");
        }

        return new UserDto(userEntity.getId(),userEntity.getUsername(),userEntity.getAge(),
                userEntity.getCreatedAt(),userEntity.getUpdatedAt(), userEntity.getPhotoURL());
    }

    @Override
    public int deletebyId(int id) {
        return userRepository.deletebyId(id);
    }


    @Override
    public UserDto updateUser(int id,UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto.getId(),userDto.getUsername(),userDto.getAge(),
                userDto.getCreatedAt(),userDto.getUpdatedAt(), userDto.getPhotoURL());
        userRepository.update_User(userEntity);
        return userDto;
    }
}
