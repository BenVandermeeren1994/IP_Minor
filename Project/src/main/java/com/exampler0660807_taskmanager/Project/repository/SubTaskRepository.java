package com.exampler0660807_taskmanager.Project.repository;

import com.exampler0660807_taskmanager.Project.domain.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Integer> {

    @Query("select subTask from SubTask subTask where subTask.taskID = ?1")
    List<SubTask> getAllSubTaskByTaskID(int taskID);

    @Modifying
    @Transactional
    void removeAllByTaskID(int taskID);

    @Modifying
    @Transactional
    @Query("update SubTask s set s.name=?1,s.description=?2,s.taskID=?3 where s.id = ?4")
    void update(String name, String description, int taskID, int id);
}








