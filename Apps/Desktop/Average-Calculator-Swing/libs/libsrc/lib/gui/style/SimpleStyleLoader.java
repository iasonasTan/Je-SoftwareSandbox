package lib.gui.style;

import lib.io.InputProperties;
import lib.io.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Style loader loads styles from properties files (see examplestyle.style from this package)
 * Loads styles and returns them as a {@link MapStyle}.
 * Uses singleton pattern and private constructor.
 */
public final class SimpleStyleLoader implements StyleLoader {
    /**
     * As mentioned above this class uses singleton pattern so
     * this is the only and only instance of this class.
     */
    public static final SimpleStyleLoader instance = new SimpleStyleLoader();

    /**
     * Private constructor prevents other classes from instantiating this class.
     */
    private SimpleStyleLoader(){}

    /**
     * Loads style from file in given {@code path}.
     * @param path the {@code path} of the style file.
     * @return returns style loaded as an implementation of {@link Style}
     */
    @Override
    public Style loadStyle(String path) {
        return loadStyle(SimpleStyleLoader.class.getResourceAsStream(path));
    }

    /**
     * Loads style from given {@link InputStream}.
     * @param input the {@link InputStream} to load style from.
     * @return returns style loaded as an implementation of {@link Style}
     */
    @Override
    public Style loadStyle(InputStream input) {
        InputProperties styleProperties = new InputProperties();
        styleProperties.load(input);
        Map<String, Object> style = new HashMap<>();

        // images
        String imagePath = styleProperties.getString("image");
        if(imagePath != null)
            style.put("image", Resources.loadImage(imagePath, SimpleStyleLoader.class));
        else
            style.put("image", null);

        // colors
        style.put("foreground", getColor(styleProperties.getString("foreground")));
        style.put("background", getColor(styleProperties.getString("background")));

        // size
        String[] sizeText = styleProperties.getString("size").split("x");
        style.put("componentSize", new Dimension(Integer.parseInt(sizeText[0]), Integer.parseInt(sizeText[1])));

        // focusable, alignment
        style.put("focusable", styleProperties.getBoolean("focusable", false));
        style.put("alignment", getAlignment(styleProperties.getString("alignment")));

        // font
        Font font;
        try {
            InputStream fontStream = SimpleStyler.class.getResourceAsStream(styleProperties.getString("font_family"));
            float size = styleProperties.getFloat("font_size", 15f);
            // noinspection all : nullpointerexception handled
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
        } catch (IOException | FontFormatException | NullPointerException e) {
            font = Font.getFont(Font.SANS_SERIF);
        }
        style.put("font", font);
        return new MapStyle(style);
    }

    /**
     * Converts alignment from string to integer from {@link SwingConstants}.
     * @param alignment Alignment as String.
     * @return alignment as integer from swing constants
     */
    public static int getAlignment(String alignment) {
        if (alignment.equals("left")) {
            return SwingConstants.LEFT;
        } else if (alignment.equals("right")) {
            return SwingConstants.RIGHT;
        } else {
            return SwingConstants.CENTER;
        }
    }

    /**
     * Converts color from string to AWT {@link Color}.
     * Color format must be this: r,g,b
     * @param text rgb color as string with the above format
     * @return The same color as AWT {@link Color}.
     */
    public static Color getColor(String text) {
        String[] colorStr = text.split(",");
        int[] color = new int[3];
        for (int i = 0; i < colorStr.length; i++)
            color[i] = Integer.parseInt(colorStr[i]);
        return new Color(color[0], color[1], color[2]);
    }

    /**
     * MapStyle is am implementation of Style that uses a map to store values.
     */
    private static class MapStyle implements Style {
        /**
         * Map contains styles and their keys
         */
        private final Map<String, Object> mStyles;

        /**
         * Constructor that initializes {@link #mStyles} field.
         * @param styles styles to set a {@link #mStyles}.
         */
        public MapStyle(Map<String, Object> styles) {
            this.mStyles = styles;
        }

        /**
         * Method returns style member by given key.
         * @param key key of style to return
         * @return returns style with given key as {@link Object}
         */
        @Override
        public Object get(String key) {
            return mStyles.get(key);
        }
    }
}
