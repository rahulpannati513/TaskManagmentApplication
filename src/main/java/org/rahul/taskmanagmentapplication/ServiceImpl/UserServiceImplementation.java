package org.rahul.taskmanagmentapplication.ServiceImpl;

import org.rahul.taskmanagmentapplication.Entity.User;
import org.rahul.taskmanagmentapplication.Payload.UserDto;
import org.rahul.taskmanagmentapplication.Repository.UserRepository;
import org.rahul.taskmanagmentapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {//userDto will have internal data to fetch and give back to the user
       //UserDto is not an Entity of Users

        User user = userDtoToEntity(userDto);//here the userDtoToEntity(userDto) is a method will convert in to the User Entity
        User savedUser = userRepository.save(user); //save the user in the database and return the saved user to map to the UserDto


        return entityToUserDto(savedUser); // we are converting the saved user to the UserDto
    }

    private User userDtoToEntity(UserDto userDto){
        User user =new User();
        user.setName(userDto.getName()); // Add this line
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto entityToUserDto(User savedUser){
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setName(savedUser.getName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());
        return userDto;
    }
}
