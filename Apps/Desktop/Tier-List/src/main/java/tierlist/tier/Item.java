package tierlist.tier;

import com.je.core.JeLib;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import tierlist.ValueHolder;
import tierlist.images.ImageViewer;

public class Item extends ImageView implements ValueHolder.Resizable  {
    public Item(Image image) {
        super(image);
        ValueHolder.instance.addItemToFitHeight(this);
        resize(ValueHolder.instance.getItemHeight(), ValueHolder.instance.getWindowWidth());

        setOnDragDetected(event -> {
            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(getImage());
            dragboard.setContent(clipboardContent);
            event.consume();
        });

        setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                ImageViewer.show(getImage());
            }
        });
    }

    @Override
    public void resize(int height, int winWidth) {
        JeLib.console().log("Scaling image...");
        setPreserveRatio(true);
        setFitHeight(height);
    }
}
