package com.fundamentos.jpa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fundamentos.jpa.model.BasicTask;
import com.fundamentos.jpa.model.CheckListTask;
import com.fundamentos.jpa.model.Task;
import com.fundamentos.jpa.model.TaskTag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskResponse(
        Long id,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy hh:mm:ss"
        )
        LocalDateTime createdAt,
        String title,
        String description,
        List<CheckListItemResponse> items,
        String tags
) {
    public TaskResponse(
            Long id,
            LocalDateTime createdAt,
            String title,
            String description,
            String tags
    ){
        this(id,createdAt,title,description,null,tags);
    }

    public TaskResponse(
            Long id,
            LocalDateTime createdAt,
            String title,
            List<CheckListItemResponse> items,
            String tags
    ){
        this(id,createdAt,title,null,items,tags);
    }

    public static TaskResponse of(BasicTask task){
        return new TaskResponse(
                task.getId(),
                task.getCreatedAt(),
                task.getTitle(),
                task.getDescription(),
                task.getTags().isEmpty() ? null :
                    task.getTags()
                    .stream()
                    .map(TaskTag::getNombre)
                    .collect(Collectors.joining(", "))
        );
    }

    public static TaskResponse of(CheckListTask task){
        return new TaskResponse(
                task.getId(),
                task.getCreatedAt(),
                task.getTitle(),
                task.getItems()
                    .stream()
                    .map(CheckListItemResponse::of)
                    .toList(),
                task.getTags().isEmpty() ? null :
                    task.getTags()
                        .stream()
                        .map(TaskTag::getNombre)
                        .collect(Collectors.joining(", "))
        );
    }

    public static TaskResponse of(Task task){
        if(task instanceof BasicTask basicTask)
            return TaskResponse.of(basicTask);
        return TaskResponse.of((CheckListTask) task);
    }
}
