package com.tasks.TaskTrackerApp.controllers;
import com.tasks.TaskTrackerApp.dto.TaskDto;
import com.tasks.TaskTrackerApp.entites.Task;
import com.tasks.TaskTrackerApp.service.TaskService;
import com.tasks.TaskTrackerApp.mapper.TaskMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/task-list/{task_list_id}/task")
public class TaskController
{
    private final TaskMapper taskMapper;
    private final TaskService taskService;

    public TaskController(TaskMapper taskMapper, TaskService taskService) {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }
    @GetMapping
    public List<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId)
    {

        return taskService.listTask(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id")UUID taskListId, @RequestBody TaskDto taskDto)
    {
        Task createdTask = taskService.createTask(taskListId,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(createdTask);
    }
    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id")UUID taskListId,@PathVariable("task_id")UUID taskId)
    {
        return taskService.getTask(taskListId,taskId).map(taskMapper::toDto);
    }
    @PutMapping("/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("task_id")UUID taskId,
            @RequestBody TaskDto taskDto)
    {
        Task updateTask = taskService
                .updateTask(taskListId,taskId,taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updateTask);
    }
    @DeleteMapping("/{task_id}")
    public ResponseEntity<String>deleteTask(@PathVariable("task_list_id")UUID taskListId,
                                            @PathVariable("task_id")UUID taskId)
    {
        taskService.deleteTask(taskListId,taskId);
        return  ResponseEntity.ok("Task Deleted Successfully");
    }

}
