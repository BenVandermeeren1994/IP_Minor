package com.exampler0660807_taskmanager.Project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class SubTask {

    @NotNull
    @NotEmpty
    @Id
    private int id;
    @NotNull
    @NotEmpty
    private int taskID;
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private String description;


    public SubTask(){}

    public SubTask(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public int getID() {
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getTaskID(){
        return this.taskID;
    }

    public void setTaskID(int taskID){
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return this.name;
    }
}