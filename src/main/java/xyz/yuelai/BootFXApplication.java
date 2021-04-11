package xyz.yuelai;

import javafx.application.Application;
import javafx.stage.Stage;

public class BootFXApplication extends Application {

    private JavaFXApplication javaFXApplication;
    protected static BootFXApplication instance;

    public BootFXApplication() {
        javaFXApplication = ContextUtil.context.getBean(JavaFXApplication.class);
        instance = this;
    }

    @Override
    public void init() throws Exception {
        javaFXApplication.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        javaFXApplication.start();
    }

    @Override
    public void stop() throws Exception {
        javaFXApplication.stop();
    }
}
