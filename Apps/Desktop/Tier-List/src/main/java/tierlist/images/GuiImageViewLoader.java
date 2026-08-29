package tierlist.images;

import com.je.core.JeLib;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import tierlist.tier.Item;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GuiImageViewLoader {
    public void loadImageViews(Path path, Pane parent) {
        try {
            List<Image> images = loadImages(path);
            for (Image image : images) {
                Item imageView = new Item(image);
                parent.getChildren().add(imageView);
            }
        } catch (IOException ioe) {
            JeLib.console().error("Could not load images.");
            JeLib.console().exception(ioe);
        }
    }

    public List<Image> loadImages(Path path) throws IOException {
        List<Image> images = new ArrayList<>();
        if (!Files.isDirectory(path)) {
            throw new IOException("Given path is not a directory.");
        }
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        for (Path file : directoryStream) {
            if (!Files.isDirectory(file)) {
                Image image = new Image(Files.newInputStream(file));
                images.add(image);
            }
        }
        directoryStream.close();
        return images;
    }
}
