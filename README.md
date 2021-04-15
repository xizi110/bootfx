简单快速整合spring boot和javafx

新建一个类继承JavaFXApplication，覆盖Start()方法。调用SpringApplication.run()即可。

```java
@SpringBootApplication
public class App extends JavaFXApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Hello JavaFX");
        label.setFont(Font.font(30));
        StackPane pane = new StackPane(label);
        primaryStage.setScene(new Scene(pane));
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
```

如果提示模块错误等信息，可以使用模块化开发也可以新建普通类，调用App类
```java
public class AppLauncher {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(App.class, args);
    }
}
```

可以自定义视图继承View类，使用spring注解@Component快速创建视图。
```java
@Slf4j
@Component
public class LoginView extends View {

    @FXML
    private Button btn;
    @FXML
    private TextField account;
    @FXML
    private TextField password;

    @FXML
    private void login(ActionEvent event) {
        if (account.getText().equals("admin")) {
            log.info("login success");
        } else {
            log.info("login failed：" + account.getText());
        }
    }
}
```

所以我们的App类可以简化为
```java
@SpringBootApplication
public class App extends JavaFXApplication {

    @Autowired
    LoginView loginView;

    @Override
    public void start() throws Exception {
        loginView.show();
    }
}
```

可以clone项目到本地，执行mvn install之后，自己项目引入
```xml
<dependency>
    <groupId>xyz.yuelai</groupId>
    <artifactId>bootfx</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

也可以下载[bootfx-1.0-SNAPSHOT.jar](https://github.com/xizi110/bootfx/releases) jar包引入
```xml
<dependency>
    <groupId>xyz.yuelai</groupId>
    <artifactId>bootfx</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!--本机路径-->
    <systemPath>xxx/xxx/bootfx-1.0-SNAPSHOT.jar</systemPath>
</dependency>
```
