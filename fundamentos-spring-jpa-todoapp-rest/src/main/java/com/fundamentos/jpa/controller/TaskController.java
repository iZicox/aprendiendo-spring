package com.fundamentos.jpa.controller;

import com.fundamentos.jpa.dto.TaskRequest;
import com.fundamentos.jpa.dto.TaskResponse;
import com.fundamentos.jpa.model.BasicTask;
import com.fundamentos.jpa.model.User;
import com.fundamentos.jpa.repos.TaskRepository;
import com.fundamentos.jpa.repos.TaskTagRepository;
import com.fundamentos.jpa.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/task/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskTagRepository taskTagRepository;

    @PostMapping("/new/basic")
    public ResponseEntity<TaskResponse> newBasicTask(@RequestBody TaskRequest taskRequest) {
        Optional<User> owner = userRepository.findByUsername(taskRequest.username());

        BasicTask task = BasicTask.builder()
                .owner(owner.orElse(null))
                .title(taskRequest.title() != null ? taskRequest.title() : "Sin titulo")
                .description(taskRequest.descripcion())
                .build();

        task = taskRepository.save(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/task/{id}")
                .build(task.getId());

        return ResponseEntity.created(uri).body(TaskResponse.of(task));
    }
}
