package com.tasks.TaskTrackerApp.service;

import com.tasks.TaskTrackerApp.dto.TaskListDto;
import com.tasks.TaskTrackerApp.entites.TaskList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService
{
    List<TaskList>listTaskList();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskListById(UUID id);
    TaskList updateTaskList(UUID id, TaskList taskList);
    void deleteTaskList(UUID id);
}
