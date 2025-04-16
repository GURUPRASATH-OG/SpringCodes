package com.tasks.TaskTrackerApp.controllers;

import com.tasks.TaskTrackerApp.dto.TaskListDto;
import com.tasks.TaskTrackerApp.entites.TaskList;
import com.tasks.TaskTrackerApp.mapper.TaskListMapper;
import com.tasks.TaskTrackerApp.mapper.TaskMapper;
import com.tasks.TaskTrackerApp.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-list")
public class TaskListController
{
    private final TaskListService  taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskList()
    {
        return taskListService.listTaskList().stream()
                .map(taskListMapper::toDto).toList();
    }
    @GetMapping("/hi")
    public String sayHi()
    {
        return "Hi";
    }
    @PostMapping()
    public TaskList createTaskList(@RequestBody TaskListDto taskListDto)
    {
        TaskList createdTaskList= taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return createdTaskList;
    }

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID id)
    {
        return taskListService.getTaskListById(id).map(taskListMapper::toDto);
    }
    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id")UUID id,@RequestBody TaskListDto dto)
    {
        TaskList updatedTaskList = taskListService.updateTaskList(id,taskListMapper.fromDto(dto));
        return taskListMapper.toDto(updatedTaskList);
    }
    @DeleteMapping("/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id")UUID id)
    {
        taskListService.deleteTaskList(id);
    }
}
