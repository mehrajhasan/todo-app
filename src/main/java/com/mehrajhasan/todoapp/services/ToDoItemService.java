package com.mehrajhasan.todoapp.services;

import com.mehrajhasan.todoapp.models.ToDoItem;
import com.mehrajhasan.todoapp.repositories.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ToDoItemService {
    @Autowired
    private ToDoItemRepository todoItemRepository;

    public Iterable<ToDoItem> getAll() {
        return todoItemRepository.findAll();
    }

    public Optional<ToDoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }

    public ToDoItem save(ToDoItem toDoItem) {
        if(toDoItem.getId() == null){
            toDoItem.setCreatedAt(Instant.now());
        }

        toDoItem.setUpdatedAt(Instant.now());
        return todoItemRepository.save(toDoItem);
    }

    public void delete(ToDoItem toDoItem) {
        todoItemRepository.delete(toDoItem);
    }
}