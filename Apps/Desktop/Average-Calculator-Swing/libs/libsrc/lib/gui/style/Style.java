package lib.gui.style;

import java.awt.*;

/**
 * Represents a GUI panel style.
 * Using {@link #get(String)} method to get a style.
 */
public interface Style {
    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Object}
     */
    Object get(String key);

    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Color}
     */
    default Color getColor(String key) {
        return (Color)get(key);
    }

    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Dimension}
     */
    default Dimension getDimension(String key) {
        return (Dimension)get(key);
    }

    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Boolean}
     */
    default boolean getBoolean(String key) {
        return (Boolean)get(key);
    }

    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Font}
     */
    default Font getFont(String key) {
        return (Font)get(key);
    }

    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Integer}
     */
    default int getInteger(String key) {
        return (Integer)get(key);
    }

    /**
     * Method returns style member by given key.
     * @param key key of style to return
     * @return returns style with given key as {@link Image}
     */
    default Image getImage(String key) {
        return (Image)get(key);
    }
}
