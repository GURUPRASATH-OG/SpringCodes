package com.tasks.TaskTrackerApp.mapper.impl;

import com.tasks.TaskTrackerApp.dto.TaskDto;
import com.tasks.TaskTrackerApp.entites.Task;
import com.tasks.TaskTrackerApp.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto)
    {
        return new Task(taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),null,null,null);
    }

    @Override
    public TaskDto toDto(Task task)
    {
        return new TaskDto(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus());
    }
}
