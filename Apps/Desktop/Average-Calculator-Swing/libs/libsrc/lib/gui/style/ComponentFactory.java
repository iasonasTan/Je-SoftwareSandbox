package lib.gui.style;

import lib.gui.ComponentBuilder;
import lib.gui.UI;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Creates a component.
 * It is possible to pass a {@link Styler} to the constructor to style components before returning them.
 * @see #ComponentFactory(Styler)
 */
public final class ComponentFactory {
    /**
     * Styler used to style the components when not null.
     */
    private final Styler mStyler;

    /**
     * Constructor initializes object with styler = null.
     */
    public ComponentFactory() {
        this(null);
    }

    /**
     * Constructor initializes object with given styler.
     * @param styler Styler to style components.
     */
    public ComponentFactory(Styler styler) {
        this.mStyler = styler;
    }

    /**
     * Returns a component of the given class.
     * <b>WARNING:</b> Given class must have a {@link String} constructor.
     * @param clazz Class of component to instantiate.
     * @param text Text passed to component.
     * @return Returns instanceof given component class as {@link JComponent}.
     * @param <T> Exact type of component.
     */
    public <T extends JComponent> T newComponent(Class<T> clazz, String text) {
        try {
            Constructor<T> strConstructor = clazz.getConstructor(String.class);
            T component = strConstructor.newInstance(text);
            return style(component);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            UI.showException(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a component of the given class.
     * <b>WARNING:</b> Given class must have a no-arg constructor.
     * @param clazz Class of component to instantiate.
     * @return Returns instanceof given component class as {@link JComponent}.
     * @param <T> Exact type of component.
     */
    public <T extends JComponent> T newComponent(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T component = constructor.newInstance();
            return style(component);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            UI.showException(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a component builder with an instance of the given class.
     * <b>WARNING:</b> Given class must have a no-arg constructor.
     * @param clazz Class of component to instantiate and put to builder.
     * @return Returns builder with instanceof given component class.
     * @param <T> Exact type of component.
     */
    @Deprecated
    public <T extends JComponent> ComponentBuilder<T> newComponentBuilder(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T component = constructor.newInstance();
            T t = style(component);
            return UI.newComponentBuilder(t);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            UI.showException(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Styles component if and only if {@link #mStyler} is not null and returns the component back.
     * @param comp Component to style if {@link #mStyler} is not null.
     * @return Returns the same component back.
     * @param <T> Type of taken component.
     */
    private <T extends JComponent> T style(T comp) {
        if(mStyler!=null)
            mStyler.styleComponent(comp);
        return comp;
    }
}
