package com.mehrajhasan.todoapp.controllers;

import org.springframework.ui.Model;
import com.mehrajhasan.todoapp.models.ToDoItem;
import com.mehrajhasan.todoapp.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoFormController {
    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-todo")
    public String showCreateFrom(Model model) {
        model.addAttribute("todoItem", new ToDoItem());
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createToDoItem(ToDoItem toDoItem) {
        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setIsComplete(toDoItem.getIsComplete());

        toDoItemService.save(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        ToDoItem todoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        toDoItemService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        ToDoItem toDoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("No ToDoItem found with id: " + id));
        model.addAttribute("todo", toDoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") Long id, ToDoItem toDoItem) {
        ToDoItem item = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(toDoItem.getIsComplete());
        item.setDescription(toDoItem.getDescription());

        toDoItemService.save(item);
        return "redirect:/";
    }
}


