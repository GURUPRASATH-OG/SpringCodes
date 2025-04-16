package com.tasks.TaskTrackerApp.dto;

import com.tasks.TaskTrackerApp.entites.TaskPriority;
import com.tasks.TaskTrackerApp.entites.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id,
                      String title,
                      String description,
                      LocalDateTime dueDate,
                      TaskPriority priority,
                      TaskStatus status)
{


}
