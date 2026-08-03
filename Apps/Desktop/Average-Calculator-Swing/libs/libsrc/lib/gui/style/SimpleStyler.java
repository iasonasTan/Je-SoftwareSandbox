package lib.gui.style;

import javax.swing.*;
import java.awt.*;

/**
 * Default implementation of {@link Styler}.
 * Can use a {@link FreeStyler} OR a {@link Style} to style the components.
 */
public final class SimpleStyler implements Styler {
    /**
     * Consumer styler used to style the components.
     */
    private final FreeStyler mFreeStyler;

    /**
     * Style used to style the components.
     */
    private final Style mStyle;

    /**
     * Constructor initializes object with a {@link FreeStyler}.
     * @param styler the styler style components.
     */
    public SimpleStyler(FreeStyler styler) {
        mFreeStyler = styler;
        mStyle = null;
    }

    /**
     * Constructor initializes object with a {@link Style}.
     * @param style the style to apply to the components.
     */
    public SimpleStyler(Style style) {
        mFreeStyler = null;
        mStyle = style;
    }

    /**
     * Styles given {@code JComponents} and returns them back.
     * @param components components to style
     * @return returns taken components.
     * @param <T> the type of the components.
     */
    @SafeVarargs
    @Override
    public final <T extends JComponent> T[] styleComponents(T... components) {
        for (T component : components) {
            styleComponent(component);
        }
        return components;
    }

    /**
     * Styles one {@link JComponent} and returns it back.
     * @param component component to style.
     * @return returns taken component.
     * @param <T> the type of the component.
     */
    @Override
    public <T extends JComponent> T styleComponent(T component) {
        if(mStyle != null) {
            component.setBackground(mStyle.getColor("background"));
            component.setForeground(mStyle.getColor("foreground"));
            component.setPreferredSize(mStyle.getDimension("componentSize"));
            component.setFont(mStyle.getFont("font"));
            component.setFocusable(mStyle.getBoolean("focusable"));
            if(component instanceof JButton)
                ((JButton)component).setHorizontalAlignment(mStyle.getInteger("alignment"));
            if(component instanceof JLabel) {
                Image image = mStyle.getImage("image");
                if(image!=null)
                    ((JLabel) component).setIcon(new ImageIcon(image));
            }
        }
        if(mFreeStyler !=null) {
            mFreeStyler.styleComponent(component);
        }
        return component;
    }

    /**
     * Functional interface used to style a component using commands.
     */
    @FunctionalInterface
    public interface FreeStyler {
        /**
         * Method that styles the given component.
         * @param component component to style.
         */
        void styleComponent(JComponent component);
    }
}
