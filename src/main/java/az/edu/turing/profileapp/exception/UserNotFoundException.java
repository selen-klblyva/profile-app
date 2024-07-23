package az.edu.turing.profileapp.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String userNotFound){super("User not found");}
}
