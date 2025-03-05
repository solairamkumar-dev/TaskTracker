package org.tt.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.tt.entity.TaskTracker;
import org.tt.util.LocalDateTimes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TaskManager {
    private List<TaskTracker> tasks;
    private Gson gson;
    private static final String file_path = "src/main/resources/tasks.json";

    public TaskManager() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimes()).create();
        tasks = loadTasks();
    }

    private List<TaskTracker> loadTasks() {
        try{
            if(Files.exists(Paths.get(file_path))) {
                String json = new String(Files.readAllBytes(Paths.get(file_path)));
                return gson.fromJson(json, new TypeToken<List<TaskTracker>>(){}.getType());
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  new ArrayList<>();
    }

    public void saveTasks() {
        try{
            String json = gson.toJson(tasks);
            Files.write(Paths.get(file_path), json.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        int id = tasks.size()+1;
        TaskTracker tracker = new TaskTracker(id, description);
        tasks.add(tracker);
        saveTasks();
        System.out.println("Task Added successfully for ID : "+id);
    }

    public void updateTask(int id, String description) {
        for(TaskTracker task : tasks) {
            if(task.getId()==id) {
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
                saveTasks();
                System.out.println("Task Updated successfully from "+task.getDescription()+" to "+description+" for ID: "+id);
                return;
            }
        }
        System.out.println("Task Id not found");
    }

    public void deleteTask(int id) {
        tasks.removeIf(taskTracker -> taskTracker.getId()==id);
        saveTasks();
        System.out.println("Task deleted successfully for ID : "+id);
    }

    public void markTask(int id, String status) {
        for(TaskTracker tracker : tasks) {
            if(tracker.getId()==id) {
                tracker.setStatus(status);
                tracker.setUpdatedAt(LocalDateTime.now());
                saveTasks();
                System.out.println("Task marked for ID : "+id+"with status : "+status);
                return;
            }
        }
        System.out.println("Task ID not found : "+id);
    }

    public void listTasks(String status) {
        for(TaskTracker tracker : tasks) {
            if(tracker.getStatus().equals(status) || status == null) {
                System.out.println(tracker);
            }
        }
    }

}
