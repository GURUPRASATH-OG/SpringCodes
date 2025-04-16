package com.tasks.TaskTrackerApp.service.impl;

import com.tasks.TaskTrackerApp.entites.TaskList;
import com.tasks.TaskTrackerApp.repository.TaskListRepository;
import com.tasks.TaskTrackerApp.service.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService
{
    //Dependency
    private final TaskListRepository taskListRepository;
    //Constructor Injection
    public TaskListServiceImpl(TaskListRepository taskListRepository)
    {
        this.taskListRepository=taskListRepository;
    }
    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null!=taskList.getId())
        {
            throw new IllegalArgumentException("Task list already has id");
        }
        if(null==taskList.getTitle() || taskList.getTitle().isBlank())
        {
            throw new IllegalArgumentException("Task Must have a title");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(
                new TaskList(null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now));

    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList)
    {
        if(null==taskList.getId())
        {
            throw new IllegalArgumentException("TaskList must have a ID");
        }
        if(!Objects.equals(taskList.getId(),id))
        {
            throw new IllegalArgumentException("Attempting to change the Id of taskList Operation Not Permitted!");
        }
        TaskList existingTaskList = taskListRepository.findById(id).orElseThrow(()->new RuntimeException("TaskList not Found"));
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setUpdated(LocalDateTime.now());
        return existingTaskList;
    }

    @Transactional
    @Override
    public void deleteTaskList(UUID id) {
        taskListRepository.deleteById(id);
    }


}
