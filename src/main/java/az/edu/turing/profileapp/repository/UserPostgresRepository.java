package az.edu.turing.profileapp.repository;

import az.edu.turing.profileapp.entity.UserEntity;
import az.edu.turing.profileapp.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class UserPostgresRepository implements UserRepository
{
    private static final String ADD_USER="INSERT INTO users(id,username,age,createdAt,updatedAt)\n"+
        "VALUES(?, ?,?,?);";
    private static final String findAllUserSQL="select *from users";
    private static final String findUserByIdSQL="select *from users where id=?";
    private static final String deleteUserByIdSQL="delete from users where id=?";
    private static final String updateUser="UPDATE users SET username = ?, age = ?, updatedAt = ?, createdAt = ? WHERE id = ?";


    @Override
    public List<UserEntity> findAll(){
        List<UserEntity> users=new ArrayList<>();
        Connection con=null;
        try{
            con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486"
            );
            PreparedStatement query=con.prepareStatement(findAllUserSQL);
            ResultSet resultSet=query.executeQuery();
            while(resultSet.next()){
                int userId=resultSet.getInt("id");
                String username=resultSet.getString("username");
                int age=resultSet.getInt("age");
                LocalDateTime createdAt=resultSet.getTimestamp("createdAt").toLocalDateTime();
                LocalDateTime updatedAt=resultSet.getTimestamp("updatedAt").toLocalDateTime();
                UserEntity userEntity = new UserEntity();
                userEntity.setId(userId);
                userEntity.setUsername(username);
                userEntity.setAge(age);
                userEntity.setCreatedAt(createdAt);
                userEntity.setUpdatedAt(updatedAt);
                users.add(userEntity);
            }


        }catch(SQLException e){
            log.error("User not found",e.getMessage());
        }
        return users;
    }


    @Override
    public void create_User(UserEntity user) {
        Connection con=null;
        try{
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");
            con.setAutoCommit(false);
            PreparedStatement query=con.prepareStatement(ADD_USER);
            query.setInt(1, (int) user.getId());
            query.setString(2,user.getUsername());
            query.setTimestamp(3, Timestamp.valueOf(user.getCreatedAt()));
            query.setTimestamp(4,Timestamp.valueOf(user.getUpdatedAt()));
            query.executeUpdate();
            con.commit();
        }catch(SQLException e){
            log.warn(e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.error("User can not be save");
            }
        }

    }

    @Override
    public UserEntity findById(int id) {
        UserEntity user=new UserEntity();
        Connection con=null;
        try{
            con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");
            PreparedStatement query=con.prepareStatement(findUserByIdSQL);
            query.setInt(1,id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                int userId=resultSet.getInt("id");
                String username=resultSet.getString("username");
                int age=resultSet.getInt("age");
                LocalDateTime createdAt=resultSet.getTimestamp("createdAt").toLocalDateTime();
                LocalDateTime updatedAt=resultSet.getTimestamp("updatedAt").toLocalDateTime();
                UserEntity userEntity = new UserEntity();
                userEntity.setId(userId);
                userEntity.setUsername(username);
                userEntity.setAge(age);
                userEntity.setCreatedAt(createdAt);
                userEntity.setUpdatedAt(updatedAt);
                return userEntity;
            }

        }catch(SQLException e){
            log.error("User not found by ID",e.getMessage());
        }
      return null;
    }

    @Override
    public int deletebyId(int id) {
         Connection con=null;
         try{
             con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                     "postgres",
                     "selback2486");
             PreparedStatement query= con.prepareStatement(deleteUserByIdSQL);
             con.setAutoCommit(false);
             query.setInt(1,id);
             int resultSet=query.executeUpdate();
             con.commit();

         }
         catch(SQLException e){
             log.warn(e.getMessage());
             try{
                 con.rollback();
             }catch(SQLException e1){
                 log.error("User can not be delete by ID",e.getMessage());
             }
         }
        return id;
    }

    @Override
    public void update_User(UserEntity user) {
        Connection con=null;
        try{
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "selback2486");
            con.setAutoCommit(false);
            PreparedStatement query=con.prepareStatement(updateUser);
            query.setInt(1, (int) user.getId());
            query.setString(2,user.getUsername());
            query.setTimestamp(3, Timestamp.valueOf(user.getCreatedAt()));
            query.setTimestamp(4,Timestamp.valueOf(user.getUpdatedAt()));
            query.executeUpdate();
            con.commit();
        }catch(SQLException e){
            log.warn(e.getMessage());
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.error("User can not be update by ID",e.getMessage());
            }
        }

    }

}
