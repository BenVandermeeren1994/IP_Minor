package com.exampler0660807_taskmanager.Project.controller;

import com.exampler0660807_taskmanager.Project.domain.SubTask;
import com.exampler0660807_taskmanager.Project.domain.Task;
import com.exampler0660807_taskmanager.Project.dto.SubTaskDTO;
import com.exampler0660807_taskmanager.Project.dto.TaskDTO;
import com.exampler0660807_taskmanager.Project.service.SubTaskService;
import com.exampler0660807_taskmanager.Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;


@Controller
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    SubTaskService subTaskService;

    @GetMapping(value={"","/","/tasks"})
    public String getTasks(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }


    @PostMapping("/tasks")
    public String addTask(@ModelAttribute @Valid Task task, BindingResult binding){
        taskService.add(createDTOfromTask(task));
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/new")
    public String goToAddPage(Model model){
        model.addAttribute("task",new Task());
        return "newTask";
    }

    @PostMapping("/remove/{id}")
    public String removeTask(@PathVariable("id") int id){
        taskService.remove(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(Model model, @PathVariable("id") int id){
        model.addAttribute("task", taskService.getTaskWithID(id));
        model.addAttribute("subtasks", subTaskService.getAllSubTasks(id));
        return "tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String goToEdit(Model model,@PathVariable("id") int id){
        model.addAttribute("task", taskService.getTaskWithID(id));
        return "editTask";
    }


    @PostMapping("/tasks/edit/{id}")
    public String edit(@ModelAttribute Task task, @PathVariable("id") int id){
        Task oldTask = taskService.getTaskWithID(id);
        task.setSubtasks(oldTask.getSubtasks());
        taskService.updateDTO(createDTOfromTask(task));
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/sub/create")
    public String goToSubTask(@PathVariable("id") int id,Model model){
        model.addAttribute("task", taskService.getTaskWithID(id));
        return "addSub";
    }


    @PostMapping("/tasks/{id}/sub/create")
    public String addSubTask(@ModelAttribute SubTask subTask, @PathVariable("id") int id){
        subTask.setTaskID(id);
        subTaskService.addSubTask(createDTOfromSubtask(subTask));
        return "redirect:/tasks";
    }


    @PostMapping("/tasks/{id}/sub/edit/{subTaskID}")
    public String editSubTask(@ModelAttribute SubTask subTask,@PathVariable("subTaskID") int subTaskID, @PathVariable("id") int taskID){
        subTask.setTaskID(taskID);
        subTask.setID(subTaskID);
        subTaskService.updateDTO(createDTOfromSubtask(subTask));
        return "redirect:/tasks";
    }

    private SubTaskDTO createDTOfromSubtask(SubTask subTask) {
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setID(subTask.getID());
        subTaskDTO.setName(subTask.getName());
        subTaskDTO.setDescription(subTask.getDescription());
        subTaskDTO.setTaskID(subTask.getTaskID());
        return subTaskDTO;
    }

    private TaskDTO createDTOfromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setID(task.getID());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setSubtasks(task.getSubtasks());
        return taskDTO;
    }
}