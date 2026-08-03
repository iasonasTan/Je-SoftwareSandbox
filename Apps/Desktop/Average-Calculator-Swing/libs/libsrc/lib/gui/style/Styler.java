package lib.gui.style;

import javax.swing.*;

/**
 * Styler is a class responsible to style one or more components.
 * It is highly recommended to make the {@code Styler} work with a {@link Style}.
 */
public interface Styler {
    /**
     * Styles given {@code JComponents} and returns them back.
     * @param components components to style
     * @return returns taken components.
     * @param <T> the type of the components.
     */
    <T extends JComponent> T[] styleComponents(T... components);

    /**
     * Styles one {@link JComponent} and returns it back.
     * @param component component to style.
     * @return returns taken component.
     * @param <T> the type of the component.
     */
    <T extends JComponent> T styleComponent(T component);
}