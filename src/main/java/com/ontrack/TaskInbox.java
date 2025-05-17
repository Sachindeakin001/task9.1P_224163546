package com.ontrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskInbox {

    // Inner class to represent a Task
    public static class Task {
        private String taskId;
        private String description;
        private boolean isCompleted;

        public Task(String taskId, String description) {
            this.taskId = taskId;
            this.description = description;
            this.isCompleted = false;
        }

        public String getTaskId() {
            return taskId;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void markAsCompleted() {
            this.isCompleted = true;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "taskId='" + taskId + '\'' +
                    ", description='" + description + '\'' +
                    ", isCompleted=" + isCompleted +
                    '}';
        }
    }

    // Main list to hold tasks
    private final List<Task> tasks;

    public TaskInbox() {
        this.tasks = new ArrayList<>();
    }

    // Add a task to the inbox
    public void addTask(String taskId, String description) {
        tasks.add(new Task(taskId, description));
    }

    // Get all tasks
    public List<Task> getTasks() {
        return tasks;
    }

    // Find a task by ID
    public Optional<Task> getTaskById(String taskId) {
        return tasks.stream()
                .filter(task -> task.getTaskId().equals(taskId))
                .findFirst();
    }

    // Get all completed tasks
    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    // Get all pending tasks
    public List<Task> getPendingTasks() {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

    // Mark a specific task as completed
    public boolean completeTask(String taskId) {
        Optional<Task> taskOptional = getTaskById(taskId);
        if (taskOptional.isPresent()) {
            taskOptional.get().markAsCompleted();
            return true;
        }
        return false;
    }
}
