package ru.makushimo.javafxexe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        initLogDir();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        logger.info("Application started.");
    }

    public static void main(String[] args) {
        launch();
    }


    private void initLogDir(){
        String logDir = System.getenv("LOCALAPPDATA") + "/myJavaFxApp/logs";
        new File(logDir).mkdirs();
        System.setProperty("log.dir", logDir);

        System.out.println("Logs will be written to: " + logDir);
    }
}