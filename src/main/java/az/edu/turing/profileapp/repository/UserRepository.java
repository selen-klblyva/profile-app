package az.edu.turing.profileapp.repository;

import az.edu.turing.profileapp.dto.UserDto;
import az.edu.turing.profileapp.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserRepository {

    public List<UserEntity> findAll();

    void create_User(UserEntity user);

    public UserEntity findById(int id);

    public int deletebyId(int id);

    void update_User(UserEntity user);


}