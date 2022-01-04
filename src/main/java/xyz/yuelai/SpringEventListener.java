package xyz.yuelai;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring启动监听器，启动成功后再启动JavaFX
 *
 * @author zhong
 */
class SpringEventListener implements ApplicationListener<SpringApplicationEvent> {

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        if (event instanceof ApplicationPreparedEvent) {
            try {
                LauncherImpl.startToolkit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (event instanceof ApplicationStartedEvent) {
            ConfigurableApplicationContext applicationContext = ((ApplicationStartedEvent) event).getApplicationContext();
            // 从容器中获取application实例
            JavaFXApplication fxApplication = applicationContext.getBean(JavaFXApplication.class);
            LauncherImpl.application = fxApplication;
            ApplicationArguments arguments = applicationContext.getBean(ApplicationArguments.class);
            LauncherImpl.launchApplication(fxApplication.getClass(), arguments.getSourceArgs());
        }
    }
}
