package com.fundamentos.jpa.controller;

import com.fundamentos.jpa.dto.TaskRequest;
import com.fundamentos.jpa.dto.TaskResponse;
import com.fundamentos.jpa.model.*;
import com.fundamentos.jpa.repos.TaskRepository;
import com.fundamentos.jpa.repos.TaskTagRepository;
import com.fundamentos.jpa.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    @PostMapping("/new/checklist")
    public ResponseEntity<TaskResponse> newChecklistTask(@RequestBody TaskRequest taskRequest) {
        Optional<User> owner = userRepository.findByUsername(taskRequest.username());

        CheckListTask task = CheckListTask.builder()
                .owner(owner.orElse(null))
                .title(taskRequest.title() != null ? taskRequest.title() : "Sin titulo")
                .build();

        taskRequest.itemns().stream()
                .map(text -> CheckListItem.builder().text(text).build())
                .forEach(task::addItem);

        task = taskRepository.save(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/task/{id}")
                .build(task.getId());

        return ResponseEntity.created(uri).body(TaskResponse.of(task));
    }

    @GetMapping("/")
    public Page<TaskResponse> getAll (@PageableDefault(page = 0, size = 5, sort = "createdAt") Pageable pageable) {

        Page<Task> result = taskRepository.findAllWithItemsAndTags(pageable);

        if(result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }

        return result.map(TaskResponse::of);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskRepository.findByIdWithItemsAndTags(id)
                .map(TaskResponse::of)
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id %d not found".formatted(id)));
    }
}
