package com.taskcli.taskcli.controller;

import com.taskcli.taskcli.model.Task;
import com.taskcli.taskcli.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;

public class TaskController {
    private TaskService taskService = new TaskService();
    private ObjectMapper objectMapper = new ObjectMapper();

    public TaskController() {
        // Register the JavaTimeModule to handle LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void addTask(String description) throws IOException {
        taskService.addTask(description);
        System.out.println("Task added successfully.");
    }

    public void updateTask(String id, String description, String status) throws IOException {
        taskService.updateTask(id, description, status);
        System.out.println("Task updated successfully.");
    }

    public void deleteTask(String id) throws IOException {
        taskService.deleteTask(id);
        System.out.println("Task deleted successfully.");
    }

    public void listAllTasks() throws IOException {
        List<Task> tasks = taskService.getAllTasks();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tasks));
    }

    public void listTasksByStatus(String status) throws IOException {
        List<Task> tasks = taskService.getTasksByStatus(status);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tasks));
    }
}