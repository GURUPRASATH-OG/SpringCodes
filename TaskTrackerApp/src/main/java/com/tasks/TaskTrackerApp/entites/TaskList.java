package com.tasks.TaskTrackerApp.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString(includeFieldNames=true)
@Entity
@Table(name="task_list")
public class TaskList
{
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id",nullable=false,updatable=false)
    private UUID id;

    @Column(name="title",nullable=false)
    private String title;

    @Column(name="description")
    private String description;
    //when we create or delete the tasklist all tasks contatined within in will saved or deleted
    @OneToMany(mappedBy="taskList",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    List<Task> tasks;
    @Column(name="created",nullable=false)
    private LocalDateTime created;

    @Column(name="updated",nullable=false)
    private LocalDateTime updated;


}
