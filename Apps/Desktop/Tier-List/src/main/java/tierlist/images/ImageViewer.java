package tierlist.images;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class ImageViewer extends Stage {
    private static ImageViewer mLastInstance = null;

    private ImageViewer(Image image) {
        super();

        ImageView imageView = new ImageView(image);
        imageView.setSmooth(true);
        Scene scene = new Scene(new StackPane(imageView));

        URL styleUrl = getClass().getResource("/style/dark_theme_style.css");
        if(styleUrl != null) {
            scene.getStylesheets().add(styleUrl.toExternalForm());
        }

        scene.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.Q)) {
                close();
            }
        });

        setScene(scene);
    }

    public static void show(Image image) {
        if(mLastInstance != null) {
            mLastInstance.close();
        }
        mLastInstance = new ImageViewer(image);
        mLastInstance.setTitle("Image Preview");
        mLastInstance.show();
    }
}
