package com.tasks.TaskTrackerApp.repository;

import com.tasks.TaskTrackerApp.entites.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID>
{
    //to find the list task present in taskList
    List<Task> findByTaskListId(UUID taskListId);
    //to get specific task present in taskList
    Optional<Task> findByTaskListIdAndId(UUID taskListId,UUID id);
    void deleteByTaskListIdAndId(UUID taskListId,UUID taskId);
}
