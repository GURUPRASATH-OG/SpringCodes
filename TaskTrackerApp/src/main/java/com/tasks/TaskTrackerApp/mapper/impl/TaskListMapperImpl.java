package com.tasks.TaskTrackerApp.mapper.impl;

import com.tasks.TaskTrackerApp.dto.TaskListDto;
import com.tasks.TaskTrackerApp.entites.Task;
import com.tasks.TaskTrackerApp.entites.TaskList;
import com.tasks.TaskTrackerApp.entites.TaskStatus;
import com.tasks.TaskTrackerApp.mapper.TaskListMapper;
import com.tasks.TaskTrackerApp.mapper.TaskMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper
{
    private final TaskMapper taskMapper;
    public TaskListMapperImpl(TaskMapper taskMapper)
    {
        this.taskMapper=taskMapper;
    }
    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks()).map(taskDtos -> taskDtos.stream().map(taskMapper::fromDto).toList()).orElse(null),null,null);
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                calculateTaskProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(task->task.stream().map(taskMapper::toDto).toList()).orElse(null)
                );
    }
    private Double calculateTaskProgress(List<Task>task)
    {
        if(null==task)
        {
            return null;
        }
        long closedTaskCount = task.stream().filter(tasks-> TaskStatus.CLOSED ==tasks.getStatus()).count();
       return (double) closedTaskCount/task.size();
    }
}
