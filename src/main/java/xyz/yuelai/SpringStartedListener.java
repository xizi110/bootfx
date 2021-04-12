package xyz.yuelai;

import javafx.application.Application;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Set;

public class SpringStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Set<Object> allSources = event.getSpringApplication().getAllSources();
        boolean app = false;
        for (Object source : allSources) {
            if (source instanceof Class) {
                if (JavaFXApplication.class.isAssignableFrom((Class) source)) {
                    app = true;
                    ConfigurableApplicationContext applicationContext = event.getApplicationContext();
                    ApplicationArguments arguments = applicationContext.getBean(ApplicationArguments.class);
                    LauncherImpl.launchApplication((Class<? extends Application>) source, arguments.getSourceArgs());
                    break;
                }
            }
        }
        if (!app) {
            throw new IllegalArgumentException("Error: not fond JavaFXApplication class");
        }
    }
}
