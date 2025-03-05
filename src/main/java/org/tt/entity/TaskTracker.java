package org.tt.entity;

import java.time.LocalDateTime;

public class TaskTracker {

    public int id;
    public String description;
    public String status;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public TaskTracker(){}

    public TaskTracker(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(LocalDateTime timeNow) {
        this.updatedAt = timeNow;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public String toString() {
        return "TaskTracker{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
