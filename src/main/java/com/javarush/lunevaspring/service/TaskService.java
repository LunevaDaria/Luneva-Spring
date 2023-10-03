package com.javarush.lunevaspring.service;


import com.javarush.lunevaspring.domain.Task;
import com.javarush.lunevaspring.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public Task getById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Page<Task> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
