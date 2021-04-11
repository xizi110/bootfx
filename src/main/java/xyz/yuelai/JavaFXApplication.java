package xyz.yuelai;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Preloader;
import org.springframework.boot.SpringApplication;

/**
 * @author zhong
 * @date 2021-04-09 15:48:37 周五
 */
public abstract class JavaFXApplication {

    public static void launch(Class<?> appClass, String[] args) {
        ContextUtil.context = SpringApplication.run(appClass, args);
        Application.launch(BootFXApplication.class, args);
    }

    protected void init() {
    }

    protected abstract void start();

    protected void stop() {
    }

    public final Application.Parameters getParameters() {
        return BootFXApplication.instance.getParameters();
    }

    public final HostServices getHostServices() {
        return BootFXApplication.instance.getHostServices();
    }

    public static String getUserAgentStylesheet() {
        return BootFXApplication.getUserAgentStylesheet();
    }

    public static void setUserAgentStylesheet(String url) {
        BootFXApplication.setUserAgentStylesheet(url);
    }

    public final void notifyPreloader(Preloader.PreloaderNotification info) {
        BootFXApplication.instance.notifyPreloader(info);
    }


}
