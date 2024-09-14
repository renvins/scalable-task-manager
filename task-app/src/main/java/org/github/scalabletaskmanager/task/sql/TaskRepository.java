package org.github.scalabletaskmanager.task.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    /**
     * Finds a task entity by its title.
     *
     * @param title the title of the task to find
     * @return the task entity with the specified title, or null if no task is found
     */
    TaskEntity findByTitle(String title);
}
