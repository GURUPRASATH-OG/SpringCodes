package com.tasks.TaskTrackerApp.service;

import com.tasks.TaskTrackerApp.entites.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService
{
    List<Task> listTask(UUID taskListId);
    Task createTask(UUID taskListId,Task task);
    Optional<Task> getTask(UUID taskListID, UUID taskID);
    Task updateTask(UUID taskListId,UUID taskId,Task task);
    void deleteTask(UUID taskListId,UUID taskId);
}
