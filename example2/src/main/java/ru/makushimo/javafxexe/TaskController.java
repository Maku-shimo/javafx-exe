package ru.makushimo.javafxexe;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskController {
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, String> nameColumn;
    @FXML private TableColumn<Task, Boolean> doneColumn;
    @FXML private TextField taskInput;

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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tasks.addAll(session.createQuery("from Task", Task.class).list());
        }
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

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
        }
        taskInput.clear();
        loadTasks();
    }

    @FXML
    private void handleDelete() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        };

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(selected);
            tx.commit();
        }
        loadTasks();
    }

    @FXML
    private void handleToggle() {
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        };

        selected.setDone(!selected.isDone());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(selected);
            tx.commit();
        }
        loadTasks();
    }
}