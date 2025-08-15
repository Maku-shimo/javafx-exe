package ru.makushimo.javafxexe3;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Task> tasks;

    public TaskService() {
        tasks = new ArrayList<>();
    }

    public List<Task> getAllTasks(){
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    public void updateTask(Task task){
        if (!tasks.contains(task)){
            tasks.add(task);
        }
    }
}
