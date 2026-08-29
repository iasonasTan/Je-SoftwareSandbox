package tierlist.tier;

import com.je.core.JeLib;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import tierlist.ValueHolder;

import java.net.URL;

public class Tier extends HBox implements ValueHolder.Resizable {
    private final Label mTitleLabel;
    private final ScrollPane mScrollPane;
    private final HBox mImagesLayout;

    public Tier(String title, String titleColor, String tierColor) {
        super();
        ValueHolder.instance.addItemToFitHeight(this);
        URL styleUrl = getClass().getResource("/style/dark_theme_style.css");
        if(styleUrl != null) {
            getStylesheets().add(styleUrl.toExternalForm());
        }

        mTitleLabel = new Label(title);
        mTitleLabel.setStyle(
            "-fx-background-color: "+titleColor+"; " +
            "-fx-text-alignment: center; "
        );
        mTitleLabel.setAlignment(Pos.CENTER);
        getChildren().add(mTitleLabel);

        mImagesLayout = new HBox();
        mImagesLayout.setStyle("-fx-background-color: "+tierColor+"; ");

        mScrollPane = new ScrollPane(mImagesLayout);
        mScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mScrollPane.setStyle("-fx-background-color: "+tierColor+"; ");
        getChildren().add(mScrollPane);

        setStyle("-fx-background-color: "+tierColor+"; ");

        setOnDragOver(event -> {
            if(event.getGestureSource() != this && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean hasImage = dragboard.hasImage();
            if(hasImage) {
                Item imageView = new Item(dragboard.getImage());
                mImagesLayout.getChildren().add(imageView);

                // Remove from parent
                if(event.getGestureSource() instanceof Item miv) {
                    if(miv.getParent() instanceof HBox hbox) {
                        hbox.getChildren().remove(miv);
                    }
                }
            }
            event.setDropCompleted(hasImage);
            event.consume();
        });
    }

    @Override
    public void resize(int height, int winWidth) {
        JeLib.console().log("Setting height = "+height+" and win width = "+winWidth);

        mTitleLabel.setPrefWidth(height);
        mTitleLabel.setPrefHeight(height);
        mTitleLabel.setFont(Font.font(height*0.6));

        //mScrollPane.setPrefWidth(winWidth);
        mScrollPane.setPrefHeight(height+ValueHolder.instance.getScrollbarHeight());

        mImagesLayout.setPrefHeight(height);

        setPrefWidth(winWidth);
        setPrefHeight(height+ValueHolder.instance.getScrollbarHeight());
    }
}
