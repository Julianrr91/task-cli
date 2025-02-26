package com.taskcli.taskcli.service;

import com.taskcli.taskcli.model.Task;
import com.taskcli.taskcli.util.FileUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService {
    private static final String FILE_PATH = "tasks.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public TaskService() {
        // Register the JavaTimeModule to handle LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void addTask(String description) throws IOException {
        List<Task> tasks = getAllTasks();
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setDescription(description);
        task.setStatus("todo");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        tasks.add(task);
        saveTasks(tasks);
    }

    public void updateTask(String id, String description, String status) throws IOException {
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setDescription(description);
                task.setStatus(status);
                task.setUpdatedAt(LocalDateTime.now());
                break;
            }
        }
        saveTasks(tasks);
    }

    public void deleteTask(String id) throws IOException {
        List<Task> tasks = getAllTasks();
        tasks.removeIf(task -> task.getId().equals(id));
        saveTasks(tasks);
    }

    public List<Task> getAllTasks() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
    }

    public List<Task> getTasksByStatus(String status) throws IOException {
        List<Task> tasks = getAllTasks();
        tasks.removeIf(task -> !task.getStatus().equals(status));
        return tasks;
    }

    private void saveTasks(List<Task> tasks) throws IOException {
        FileUtil.writeToFile(FILE_PATH, objectMapper.writeValueAsString(tasks));
    }
}