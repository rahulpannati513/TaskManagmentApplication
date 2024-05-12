package org.rahul.taskmanagmentapplication.Payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
}
