package com.tasks.TaskTrackerApp.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode
@ToString(includeFieldNames =true)
@Entity
@Table(name="tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",nullable = false,updatable = false)
    private UUID id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="due_date") //snake case for naming database column.
    private LocalDateTime dueDate; //camelcase for naming java variables.

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)// to set the enum as string instead of 0 if dont set this priority willl be saved as 0
    @Column(name="priority",nullable=false)
    private TaskPriority priority;

    @ManyToOne()
    @JoinColumn(name="task_list_id")
    private TaskList taskList;

    @Column(name="created",nullable = false)
    private LocalDateTime created;

    @Column(name="updated",nullable = false)
    private LocalDateTime updated;



}
