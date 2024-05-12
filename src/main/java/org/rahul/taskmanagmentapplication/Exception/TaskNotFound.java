package org.rahul.taskmanagmentapplication.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class TaskNotFound  extends  RuntimeException{
    private String message;
    public TaskNotFound(String message){
        super(message);
        this.message = message;
    }

}
