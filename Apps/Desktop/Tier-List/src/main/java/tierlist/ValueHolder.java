package tierlist;

import com.je.core.JeLib;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("FieldCanBeLocal")
public final class ValueHolder {
    public static final ValueHolder instance = new ValueHolder();

    private final int SCROLLBAR_HEIGHT = 0;
    private final int COLS = 6;
    private int mItemHeight = 0;
    private int mWindowWidth = 0;
    private final List<Resizable> mItemsToResize = new ArrayList<>();

    private ValueHolder() {}

    public int getWindowWidth() {
        return mWindowWidth;
    }

    public int getCols() {
        return COLS;
    }

    public int getScrollbarHeight() {
        return SCROLLBAR_HEIGHT;
    }

    public int getItemHeight() {
        return mItemHeight;
    }

    public void addItemToFitHeight(Resizable res) {
        mItemsToResize.add(res);
    }

    public void init(Stage primaryStage) {
        JeLib.console().log("Initializing...");
        class ValueListener implements ChangeListener<Number> {
            final Dimension dimension;

            ValueListener(Dimension dimension) {
                this.dimension = dimension;
            }

            enum Dimension {
                WIDTH,
                HEIGHT
            }

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                JeLib.console().log("Window resized ("+dimension.name()+")...");

                switch (dimension) {
                    case HEIGHT:
                        mItemHeight = newValue.intValue()/getCols();
                        break;
                    case WIDTH:
                        mWindowWidth = newValue.intValue();
                        break;
                }

                mItemsToResize.forEach(item -> item.resize(mItemHeight, mWindowWidth));

                Parent root = primaryStage.getScene().getRoot();
                root.requestLayout();
            }
        }
        primaryStage.getScene().widthProperty().addListener(new ValueListener(ValueListener.Dimension.WIDTH));
        primaryStage.getScene().heightProperty().addListener(new ValueListener(ValueListener.Dimension.HEIGHT));

        mItemHeight = (int)primaryStage.getHeight()/getCols();
        mWindowWidth = (int)primaryStage.getWidth();
        mItemsToResize.forEach(item -> item.resize(mItemHeight, mWindowWidth));
    }

    public interface Resizable {
        void resize(int height, int winWidth);
    }
}
