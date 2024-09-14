package org.github.scalabletaskmanager.task.sql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class TaskEntity {

    public TaskEntity() {
    }

    public TaskEntity(long id, String title, String description, TaskStatus status, long creatorId, String creatorUsername, String creatorFullName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.creatorFullName = creatorFullName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "creatorId")
    private long creatorId;

    @Column(name = "creatorUsername")
    private String creatorUsername;

    @Column(name = "creatorFullName")
    private String creatorFullName;
}
