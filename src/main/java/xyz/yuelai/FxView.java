package xyz.yuelai;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import xyz.yuelai.annotation.FxmlView;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

abstract class FxView {
    private Parent root;

    @PostConstruct
    void init() {
        loadFxml();
    }

    public Parent getRoot() {
        // 加载 fxml 获取 root node
        if (root == null) {
            loadFxml();
        }
        return root;
    }

    protected String fxml() {
        FxmlView fxmlView = AnnotationUtils.findAnnotation(getClass(), FxmlView.class);
        if (fxmlView != null) {
            String fxmlPath = fxmlView.fxmlPath();
            if (StringUtils.hasText(fxmlPath)) {
                return fxmlView.fxmlPath();
            }
        }
        return getClass().getSimpleName() + ".fxml";
    }

    protected ResourceBundle resourceBundle() {
        try {
            return ResourceBundle.getBundle("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadFxml() {
        try {
            root = getLoader().load();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private URL resolveLocation() {
        String fxmlPath = fxml();
        URL url = getClass().getResource(fxmlPath);
        if (url == null) {
            throw new IllegalStateException("file location is not exit：" + fxmlPath);
        }
        return url;
    }

    private FXMLLoader getLoader() {
        FXMLLoader loader = new FXMLLoader(resolveLocation(), resourceBundle());
        loader.setController(this);
        loader.setRoot(null);
        return loader;
    }

}
