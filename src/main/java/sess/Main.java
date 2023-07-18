package sess;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Expert.getInstance();
        FileHandelling.readFromFile();
        stage.setMinWidth(400);
        stage.setMinHeight(700);
        stage.setTitle("sess");
        Scenes.LoginScene(stage);
        stage.show();
    }
}
