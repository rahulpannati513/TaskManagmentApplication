package org.rahul.taskmanagmentapplication.Service;

import org.rahul.taskmanagmentapplication.Payload.TaskDto;

import java.util.List;

public interface TaskService {

    public TaskDto  saveTask(long userId, TaskDto taskDto);

    public List<TaskDto> getAllTasks(long userId);

    public TaskDto getTask(long userid, long taskid);

    public void deleteTask(long userid, long taskid);







}
