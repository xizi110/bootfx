package xyz.yuelai;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

abstract class FxView implements Initializable {
    private Parent root;
    private FXMLLoader loader;

    public Parent getRoot() {
        if (this instanceof View) {
            // 加载 fxml 获取 root node
            if (root == null) {
                loadFxml();
            }
        } else {
            loadFxml();
        }
        return root;
    }

    protected String fxml() {
        return getClass().getSimpleName() + ".fxml";
    }

    protected ResourceBundle resourceBundle() {
        try {
            return ResourceBundle.getBundle("message");
        } catch (Exception ignored) {
        }
        return null;
    }

    private void loadFxml() {
        try {
            FXMLLoader loader = getLoader();
            loader.setRoot(null);
            root = getLoader().load();
        } catch (IOException e) {
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

    public FXMLLoader getLoader() {
        if (loader == null) {
            loader = new FXMLLoader(resolveLocation(), resourceBundle());
            loader.setController(this);
        }
        return loader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
