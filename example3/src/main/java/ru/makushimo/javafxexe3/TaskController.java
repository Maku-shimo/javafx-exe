package ru.makushimo.javafxexe3;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TaskController {
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> nameColumn;
    @FXML private TableColumn<Task, Boolean> doneColumn;
    @FXML private TextField taskInput;

    private final TaskService taskService = new TaskService();

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        doneColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDone()));
        taskTable.setItems(tasks);
        loadTasks();
    }

    private void loadTasks() {
        tasks.clear();
        tasks.addAll(taskService.getAllTasks());
    }

    @FXML
    private void handleAdd() {
        String name = taskInput.getText();
        if (name.isEmpty()) {
            return;
        };

        Task task = new Task();
        task.setName(name);
        task.setDone(false);

        taskService.addTask(task);

        taskInput.clear();
        loadTasks();
    }

    @FXML
    private void handleDelete() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        };

        taskService.deleteTask(selected);

        loadTasks();
    }

    @FXML
    private void handleToggle() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        };

        selected.setDone(!selected.isDone());

        taskService.updateTask(selected);

        loadTasks();
    }
}