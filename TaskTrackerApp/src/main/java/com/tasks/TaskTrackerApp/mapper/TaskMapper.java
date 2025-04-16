package com.tasks.TaskTrackerApp.mapper;

import com.tasks.TaskTrackerApp.dto.TaskDto;
import com.tasks.TaskTrackerApp.entites.Task;

public interface TaskMapper
{
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);

}
