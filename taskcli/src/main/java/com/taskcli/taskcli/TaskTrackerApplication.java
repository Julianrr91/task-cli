package com.taskcli.taskcli;

import java.io.IOException;

import com.taskcli.taskcli.controller.TaskController;

public class TaskTrackerApplication {
    public static void main(String[] args) {
        TaskController controller = new TaskController();
        try {
            switch (args[0]) {
                case "add":
                    controller.addTask(args[1]);
                    break;
                case "update":
                    controller.updateTask(args[1], args[2], args[3]);
                    break;
                case "delete":
                    controller.deleteTask(args[1]);
                    break;
                case "list":
                    controller.listAllTasks();
                    break;
                case "list-done":
                    controller.listTasksByStatus("done");
                    break;
                case "list-in-progress":
                    controller.listTasksByStatus("in-progress");
                    break;
                case "list-todo":
                    controller.listTasksByStatus("todo");
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing arguments.");
        }
    }
}