package com.exampler0660807_taskmanager.Project.service;

import com.exampler0660807_taskmanager.Project.domain.SubTask;
import com.exampler0660807_taskmanager.Project.dto.SubTaskDTO;
import com.exampler0660807_taskmanager.Project.repository.SubTaskRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubTaskService {

    SubTaskRepository repository;

    public SubTask getSubTaskWithID(int id){
        Optional<SubTask> optionalSubTask = repository.findById(id);
        return optionalSubTask.orElseThrow(() -> new IllegalIdentifierException("This ID is not found."));
    }

    public void addSubTask(SubTaskDTO dto){
        repository.save(entityConverter(dto));
    }

    public void remove(int id){
        repository.deleteById(id);
    }

    public void updateDTO(SubTaskDTO subTaskDTO){
        SubTask subTask = entityConverter(subTaskDTO);
        repository.update(subTask.getName(),subTask.getDescription(),subTask.getTaskID(),subTask.getID());
    }

    public List<SubTask> getAllSubTasks(int id){
        return repository.getAllSubTaskByTaskID(id);
    }

    public List<SubTask> getAll(){
        return repository.findAll();
    }

    public SubTask entityConverter(SubTaskDTO subTaskDTO){
        SubTask subTask = new SubTask();
        subTask.setName(subTaskDTO.getName());
        subTask.setDescription(subTaskDTO.getDescription());
        subTask.setID(subTaskDTO.getID());
        subTask.setTaskID(subTaskDTO.getTaskID());
        return subTask;
    }
}