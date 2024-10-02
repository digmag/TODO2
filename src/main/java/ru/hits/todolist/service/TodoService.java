package ru.hits.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hits.todolist.dto.CreateDTO;
import ru.hits.todolist.dto.UploadDTO;
import ru.hits.todolist.entity.TodoListItem;
import ru.hits.todolist.repository.TodoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public List<TodoListItem> viewAll(){
        return repository.findAll();
    }


    @Transactional
    public TodoListItem addItem(CreateDTO createDTO, UUID id){
        var todo = new TodoListItem();
        todo = new TodoListItem(
                UUID.randomUUID(),
                false,
                createDTO.getTitle()
        );
        repository.save(todo);
        return todo;
    }

    public void deleteItem(UUID id){
        repository.deleteById(id);
    }

    @Transactional
    public TodoListItem editItem(UUID id, CreateDTO createDTO){
        var todo = repository.findById(id).orElse(null);
        todo.setTitle(createDTO.getTitle());
        repository.save(todo);
        return todo;
    }

    public TodoListItem changeStatus(UUID id){
        var todo = repository.findById(id).orElse(null);
        todo.setDone(!todo.isDone());
        repository.save(todo);
        return todo;
    }

    @Transactional
    public List<TodoListItem> upload(List<UploadDTO> createDTOS){
        var listt = repository.findAll();
        listt.forEach(repository::delete);
        createDTOS.forEach(item -> {
            var todoItem = new TodoListItem(
                    UUID.randomUUID(),
                    item.isDone(),
                    item.getTitle()
            );
            repository.save(todoItem);
        });
        return repository.findAll();
    }
}
