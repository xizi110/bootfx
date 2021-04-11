package xyz.yuelai;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhong
 * @date 2021-04-09 17:45:51 周五
 * 抽象类视图，用户的视图需要继承此类。视图包含
 * stage、scene。
 */
public abstract class View extends FxView {

    private Stage stage;
    private Scene scene;

    @Autowired
    private AppConfig appConfig;

    public final Stage getStage() {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle(appConfig.getTitle());
            if (appConfig.getIconImage() != null) {
                stage.getIcons().add(appConfig.getIconImage());
            }
            stage.setScene(getScene());
        }
        return stage;
    }

    public final Scene getScene() {
        if (scene == null) {
            scene = new Scene(getRoot());
        }
        return scene;
    }

    public final void show() {
        getStage().show();
    }

    public final void hide() {
        getStage().hide();
    }

//    public void reloadView() {
//        FXMLLoader loader = getLoader();
//        loader.setRoot(null);
//        try {
//            getScene().setRoot(loader.load());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
