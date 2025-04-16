package com.tasks.TaskTrackerApp.mapper;

import com.tasks.TaskTrackerApp.dto.TaskListDto;
import com.tasks.TaskTrackerApp.entites.TaskList;

public interface TaskListMapper
{
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
