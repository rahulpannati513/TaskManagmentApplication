package org.rahul.taskmanagmentapplication.Service;

import org.rahul.taskmanagmentapplication.Payload.UserDto;
import org.rahul.taskmanagmentapplication.Repository.UserRepository;

public interface UserService  {
  public  UserDto createUser(UserDto userDto);

}
