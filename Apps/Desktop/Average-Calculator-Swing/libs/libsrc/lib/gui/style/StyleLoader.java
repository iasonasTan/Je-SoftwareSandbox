package lib.gui.style;

import java.io.InputStream;

/**
 * Style loader is a class responsible to load style from a given {@link InputStream} or {@code Path}.
 */
public interface StyleLoader {
    /**
     * Loads style from given {@link InputStream}.
     * @param inputStream the {@link InputStream} to load style from.
     * @return returns style loaded as an implementation of {@link Style}
     */
    Style loadStyle(InputStream inputStream);

    /**
     * Loads style from file in given {@code path}.
     * @param path the {@code path} of the style file
     * @return returns style loaded as an implementation of {@link Style}
     */
    Style loadStyle(String path);
}
