package org.rahul.taskmanagmentapplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{
    private  String message;
    public UserNotFound(String message){
        super(message);// mana message ni super class lo chupistadi
        this.message = message;
    }
}
