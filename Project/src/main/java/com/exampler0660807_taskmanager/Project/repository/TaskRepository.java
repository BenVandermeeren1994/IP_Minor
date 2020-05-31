package com.exampler0660807_taskmanager.Project.repository;

import com.exampler0660807_taskmanager.Project.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Transactional
    @Query("update Task t set t.name = ?1,t.dueDate = ?2,t.description = ?3")
    void updateTask(String name, LocalDateTime dueDate, String description);

}