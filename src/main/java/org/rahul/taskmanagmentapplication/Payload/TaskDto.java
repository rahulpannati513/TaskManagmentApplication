package org.rahul.taskmanagmentapplication.Payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDto {
    private long id;
    private String taskName;
}
