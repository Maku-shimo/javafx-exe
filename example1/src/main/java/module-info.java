module ru.makushimo.javafxexe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.slf4j;
    requires ch.qos.logback.classic;

    opens ru.makushimo.javafxexe to javafx.fxml;
    exports ru.makushimo.javafxexe;
}