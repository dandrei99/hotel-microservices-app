package hotel.user_service.exception.exceptions;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){
        super("Email or password incorrect");
    }
}
