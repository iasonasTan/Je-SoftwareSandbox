package tierlist;

import com.jjfx.utils.InputWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import tierlist.tier.TiersScene;

import java.nio.file.Paths;

public class Main extends Application {
    static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        InputWindow window = new InputWindow("Tier List - Path is needed", "Enter path of images folder.", stage, "", path -> {
            TiersScene tiersScene = new TiersScene(Paths.get(path));
            stage.setScene(new Scene(new ScrollPane(tiersScene)));
            ValueHolder.instance.init(stage);
            stage.sizeToScene();
            stage.setTitle("Tier List App");
            stage.setOnCloseRequest(_ -> Platform.exit());
            stage.show();
        });
        window.addActionOk();
        window.showWindow(true);
    }
}
