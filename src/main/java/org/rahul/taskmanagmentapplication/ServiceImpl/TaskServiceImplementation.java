package org.rahul.taskmanagmentapplication.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.rahul.taskmanagmentapplication.Entity.Task;
import org.rahul.taskmanagmentapplication.Entity.User;
import org.rahul.taskmanagmentapplication.Exception.APIException;
import org.rahul.taskmanagmentapplication.Exception.TaskNotFound;
import org.rahul.taskmanagmentapplication.Exception.UserNotFound;
import org.rahul.taskmanagmentapplication.Payload.TaskDto;
import org.rahul.taskmanagmentapplication.Repository.TaskRepository;
import org.rahul.taskmanagmentapplication.Repository.UserRepository;
import org.rahul.taskmanagmentapplication.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public TaskDto saveTask(long userId, TaskDto taskDto) {  // we need to write a code to store the taskData in the database

        User user =userRepository.findById(userId).orElseThrow(()->new UserNotFound(String.format("User with id %d not found",userId)));
        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(user);  // we are storing the user obj in the task obj so that we can store the user id in the task table
        // after setting the user, we are storing the data in DB
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDto.class);

    }

    @Override
    public List<TaskDto> getAllTasks(long userId) {
      //  User user =userRepository.findById(userId).orElseThrow(()->new
        //  UserNotFound(String.format("User with id %d not found",userId)));

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(String.format("User with id %d not found", userId)));
        List<Task>  tasks = taskRepository.findAllByUserId(userId);

        return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(long userid, long taskid) {
        User user = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User with id %d not found", userid)));
       Task task = taskRepository.findById(taskid).orElseThrow(()->new TaskNotFound(String.format("Task with id %d not found",taskid)));
       if(user.getId() != task.getUser().getId())
           throw new APIException(String.format("Task with id %d not belongs to user Id %d",taskid,userid));
        return  modelMapper.map(task,TaskDto.class);
    }

    @Override
    public void deleteTask(long userid, long taskid) {
        User user = userRepository.findById(userid).orElseThrow(() -> new UserNotFound(String.format("User with id %d not found", userid)));
        Task task = taskRepository.findById(taskid).orElseThrow(()->new TaskNotFound(String.format("Task with id %d not found",taskid)));
         taskRepository.deleteById(taskid);
    }


}
