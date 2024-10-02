package ru.hits.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDTO {
    private String title;
}
