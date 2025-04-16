package com.tasks.TaskTrackerApp.repository;

import com.tasks.TaskTrackerApp.entites.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID>
{
}
