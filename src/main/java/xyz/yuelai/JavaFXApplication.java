package xyz.yuelai;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public abstract class JavaFXApplication extends Application {

    @Override
    public final void start(Stage primaryStage) throws Exception {
        start();
    }

    /**
     * 不需要默认的stage，所以替换 start(Stage primaryStage)
     * @throws Exception
     */
    public abstract void start() throws Exception;
}
