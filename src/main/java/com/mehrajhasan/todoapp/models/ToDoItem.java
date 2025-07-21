package com.mehrajhasan.todoapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;

//lombok saves time from manually writing out methods
@Getter
@Setter
@Entity
@Table(name="todo_items")
public class ToDoItem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //lombok auto gens methods for these, so just need these details

    //string for description of item
    private String description;

    //track when todo is complete
    private Boolean isComplete;

    //track when item was created
    private Instant createdAt;

    //track wehn the item was updated
    private Instant updatedAt;

    @Override
    public String toString() {
        return String.format("TodoItem{id=%d, description=%s, isComplete=%s, createdAt=%s, updatedAt=%s}", id, description, isComplete, createdAt, updatedAt);
    }


}
