package xyz.yuelai;

import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
@ConfigurationProperties(prefix = "app")
class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    private String title;
    private String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private Image iconImage;

    public Image getIconImage() {
        if (icon != null && iconImage == null) {
            URL resource = getClass().getResource(icon);
            if (resource != null) {
                iconImage = new Image(resource.toExternalForm());
            } else {
                logger.warn("app icon file is not found: " + icon);
            }
        }
        return iconImage;
    }
}
