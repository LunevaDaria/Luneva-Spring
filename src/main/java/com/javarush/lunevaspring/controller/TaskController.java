package com.javarush.lunevaspring.controller;

import com.javarush.lunevaspring.domain.Task;
import com.javarush.lunevaspring.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public String indexPage(Model model, @PathVariable int id,Pageable pageable) {
        pageable = PageRequest.of(id-1, 10);
        model.addAttribute("tasks", taskService.getAll(pageable));
        return "tasks/index";
    }


    @GetMapping("/task-page/{id}")
    public String showTaskPage(@PathVariable long id, Model model){
        model.addAttribute("task", taskService.getById(id));
        return "tasks/task-page";
    }

    @GetMapping("/create")
    public String createTaskPage(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/create";
    }

    @PostMapping("/create")
    public String createTask(Task task){
        if (!task.getDescription().isEmpty()){
            taskService.saveTask(task);
        }else {
            return "redirect:/tasks/1";
        }
        return "redirect:/tasks/task-page/" + task.getId();
        }

    @GetMapping("/update/{id}")
    public String updatePlayerPage(@PathVariable long id, Model model){
        model.addAttribute("task", taskService.getById(id));
        return "tasks/update";
    }
    @PostMapping("/update/{id}")
    public String updatePlayer(@PathVariable long id, Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks/task-page/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable long id) {
        taskService.deleteById(id);
        return "redirect:/tasks/1";
    }

}
