package tierlist.tier;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tierlist.ValueHolder;
import tierlist.images.GuiImageViewLoader;

import java.net.URL;
import java.nio.file.Path;

public class TiersScene extends BorderPane implements ValueHolder.Resizable {
    private final ScrollPane mScrollPane;

    public TiersScene(Path path) {
        super();
        ValueHolder.instance.addItemToFitHeight(this);
        URL styleUrl = getClass().getResource("/style/dark_theme_style.css");
        if(styleUrl != null) {
            getStylesheets().add(styleUrl.toExternalForm());
        }

        HBox hbox = new HBox();
        new GuiImageViewLoader().loadImageViews(path, hbox);

        mScrollPane = new ScrollPane(hbox);
        mScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setBottom(mScrollPane);

        VBox tiersVbox = new VBox();

        tiersVbox.getChildren().addAll(
                new Tier("S", "#800000", "#400000"),
                new Tier("A", "#998c00", "#4d4600"),
                new Tier("B", "#006603", "#003301"),
                new Tier("C", "#004078", "#002240"),
                new Tier("D", "#660059", "#33002d")
        );

        setCenter(tiersVbox);
        tiersVbox.setPrefWidth(900);
        tiersVbox.setPrefHeight(750);
    }

    @Override
    public void resize(int height, int winWidth) {
        System.out.println("Scaling image...");
        mScrollPane.setPrefWidth(winWidth);
        mScrollPane.setPrefHeight(height+ValueHolder.instance.getScrollbarHeight());
    }

}