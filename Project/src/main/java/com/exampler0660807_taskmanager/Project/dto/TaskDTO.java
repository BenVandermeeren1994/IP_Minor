package com.exampler0660807_taskmanager.Project.dto;

import com.exampler0660807_taskmanager.Project.domain.SubTask;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskDTO {

    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private LocalDateTime dueDate;
    @NotNull
    private  String dueDateToString;
    @NotNull
    private String description;
    @Id
    @GeneratedValue
    @NotNull
    @NotEmpty
    private int id;
    @Transient
    private List<SubTask> subTasks;

    public int getID() {
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    //TODO waarom altijd null?
    public void setDueDate(LocalDateTime dueDate) {
        if(dueDate == null){
            dueDate = LocalDateTime.now();
        }
        this.dueDate = dueDate;
        this.dueDateToString = DateTimeFormatter.ISO_DATE.format(dueDate) + " " + DateTimeFormatter.ISO_TIME.format(dueDate);
        System.out.println(this.dueDateToString);
    }

    public String getDueDateToString() {
        return dueDateToString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTask> getSubtasks(){
        return this.subTasks;
    }

    public void setSubtasks(List<SubTask> subtasks){
        this.subTasks = subtasks;
    }



    public void addSubTask(SubTask subTask){
        //TODO in nav steken
        int id = 1;
        if (this.subTasks.size()>0){
            id = this.subTasks.get(subTasks.size()-1).getID()+1;
        }
        subTask.setID(id);
        this.subTasks.add(subTask);
    }
}