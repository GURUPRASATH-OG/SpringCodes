package com.tasks.TaskTrackerApp.service.impl;

import com.tasks.TaskTrackerApp.entites.Task;
import com.tasks.TaskTrackerApp.entites.TaskList;
import com.tasks.TaskTrackerApp.entites.TaskPriority;
import com.tasks.TaskTrackerApp.entites.TaskStatus;
import com.tasks.TaskTrackerApp.repository.TaskListRepository;
import com.tasks.TaskTrackerApp.repository.TaskRepository;
import com.tasks.TaskTrackerApp.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService
{
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository,TaskListRepository taskListRepository) {
        this.taskListRepository=taskListRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> listTask(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task)
    {
        if(null!=  task.getStatus())
        {
            throw new IllegalArgumentException("Task Id will be generated automatically");
        }
        if(null==task.getTitle() || task.getTitle().isBlank())
        {
            throw new IllegalArgumentException("Task Title is required");
        }
        TaskPriority taskPriority= Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(()->new IllegalArgumentException("Invalid Task List Id Provided1"));
        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
                );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListID, UUID taskID) {
        return taskRepository.findByTaskListIdAndId(taskListID, taskID);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task updatetask) {
        if(null==updatetask.getId())
        {
            throw new IllegalArgumentException("Task must have an ID");
        }
        if(!Objects.equals(taskId,updatetask.getId()))
        {
            throw new IllegalArgumentException("Task Id Do Not Match!");
        }
        if(null==updatetask.getPriority())
        {
            throw new IllegalArgumentException("Task Priority is required");
        }
        if(null==updatetask.getStatus())
        {
            throw new IllegalArgumentException("Task Status is Required");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId,taskId).orElseThrow(()->new IllegalArgumentException("Task Not found"));
        existingTask.setTitle(updatetask.getTitle());
        existingTask.setDescription(updatetask.getDescription());
        existingTask.setDueDate(updatetask.getDueDate());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setPriority(updatetask.getPriority());
        existingTask.setStatus(updatetask.getStatus());
        return taskRepository.save(existingTask);
    }

    @Transactional // to ensure consistency of database
    @Override
    public void deleteTask(UUID taskListId, UUID taskId)
    {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
