package xyz.yuelai;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring启动监听器，启动成功后再启动JavaFX
 * @author zhong
 */
public class SpringStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        // 从容器中获取application实例
        JavaFXApplication fxApplication = applicationContext.getBean(JavaFXApplication.class);
        LauncherImpl.application = fxApplication;
        ApplicationArguments arguments = applicationContext.getBean(ApplicationArguments.class);
        LauncherImpl.launchApplication(fxApplication.getClass(), arguments.getSourceArgs());
    }
}
