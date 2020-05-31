package com.exampler0660807_taskmanager.Project.service;

import com.exampler0660807_taskmanager.Project.domain.Task;
import com.exampler0660807_taskmanager.Project.dto.TaskDTO;
import com.exampler0660807_taskmanager.Project.repository.SubTaskRepository;
import com.exampler0660807_taskmanager.Project.repository.TaskRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    TaskRepository taskRepository;
    SubTaskRepository subTaskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }
    
    public Task getTaskWithID(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseThrow(() -> new IllegalIdentifierException("This ID is not found."));
    }

    public void add(TaskDTO taskDTO) {
        taskRepository.save(entityConverter(taskDTO));
    }

    public void remove(int id) {
        taskRepository.deleteById(id);
        subTaskRepository.removeAllByTaskID(id);
    }

    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setID(taskDTO.getID());
        task.setSubtasks(taskDTO.getSubtasks());
        task = taskRepository.save(task);
        return taskConverter(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    private TaskDTO taskConverter(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setID(task.getID());
        taskDTO.setSubtasks(task.getSubtasks());
        return taskDTO;
    }

    public void updateDTO(TaskDTO taskDTO) {
        taskRepository.updateTask(taskDTO.getName(), taskDTO.getDueDate(), taskDTO.getDescription());
    }

    public Task entityConverter(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDueDate(taskDTO.getDueDate());
        task.setDescription(taskDTO.getDescription());
        task.setID(taskDTO.getID());
        task.setSubtasks(taskDTO.getSubtasks());
        return task;
    }
}