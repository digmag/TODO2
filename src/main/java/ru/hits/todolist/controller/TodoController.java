package ru.hits.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hits.todolist.dto.CreateDTO;
import ru.hits.todolist.dto.UploadDTO;
import ru.hits.todolist.entity.TodoListItem;
import ru.hits.todolist.service.TodoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("")
    public List<TodoListItem> getList(){
        return todoService.viewAll();
    }


    @PostMapping("")
    public TodoListItem create(@RequestBody CreateDTO createDTO, @RequestParam(required = false, name = "id") UUID id){
        return todoService.addItem(createDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        todoService.deleteItem(id);
    }

    @PatchMapping("/{id}")
    public TodoListItem edit(@PathVariable UUID id, @RequestBody CreateDTO createDTO){
        return todoService.editItem(id, createDTO);
    }

    @PatchMapping("/change/{id}")
    public TodoListItem changeStatus(@PathVariable UUID id){
        return todoService.changeStatus(id);
    }

    @PostMapping("/upload")
    public List<TodoListItem> uploadList(@RequestBody List<UploadDTO> createDTOS){
        return  todoService.upload(createDTOS);
    }
}
