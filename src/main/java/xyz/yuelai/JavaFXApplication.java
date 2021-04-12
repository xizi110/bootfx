package xyz.yuelai;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public abstract class JavaFXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        bootApp();
    }

    public abstract void bootApp() throws Exception;
}