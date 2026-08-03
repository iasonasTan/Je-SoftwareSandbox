package app.avg.gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import lib.gui.AbstractScreen;
import lib.gui.style.*;
import java.io.*;
import javax.swing.border.*;
import lib.gui.UI;
import lib.gui.layout.VerticalFlowLayout;
import app.avg.computer.*;

public class Gui extends AbstractScreen {
    private final Computer mComputer;

    private final JButton mAddValueButton;
    private final JTextField mInputNumberField, mResultTextField;

    // Initialize GUI components
    {
        setBackground(new Color(66,90,193));
        setLayout(new VerticalFlowLayout(5, 5));
        Styler styler = new ComponentStyler();
        mInputNumberField = addComponentBuilder(new JTextField(), null)
            .style(styler)
            .build();
        mAddValueButton = addComponentBuilder(new JButton("Add"), null)
            .style(styler)
            .build();
        mResultTextField = addComponentBuilder(new JTextField(), null)
            .style(styler)
            .setText("Average: 0.00")
            .setEditable(false)
            .build();
        mAddValueButton.addActionListener(new AddValueListener());
        mInputNumberField.addKeyListener(new AddValueListener());
    }
    
    public Gui(Computer computer) {
        mComputer = computer;
        mInputNumberField.requestFocus();
    }

    @Override
    protected Image icon() {
        return null;
    }

    @Override
    protected Image background() {
        return null;
    }

    @Override
    protected String title() {
        return "Average Counter";
    }

    private final class ComponentStyler implements Styler {
        private Font mFont;

        public ComponentStyler() {
            try {
                mFont = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/font/droidserif-bold.ttf")
                ).deriveFont(22f);
            } catch (IOException | FontFormatException ffe) {
                UI.showException(ffe);
            }
        }

        @Override
        @Deprecated
        @SuppressWarnings("all")
        public <T extends JComponent> T[] styleComponents(T... components) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T extends JComponent> T styleComponent(T component) {
            component.setForeground(new Color(194,169,126));
            component.setBackground(new Color(61,86,129));
            component.setPreferredSize(new Dimension(280,60));
            component.setFont(mFont);
            component.setBorder(BorderFactory.createLineBorder(new Color(51,76,119), 4));
            if(component instanceof JTextField jtf) {
                jtf.setHorizontalAlignment(SwingConstants.CENTER);
            }
            return component;
        }
    }

    private final class AddValueListener implements ActionListener, KeyListener {
        @Override
        public void actionPerformed(ActionEvent ignored) {
            Double val = getTypedValue();
            if(val!=null) {
                mComputer.add(val);
                calculateAverage();
            } else
                JOptionPane.showMessageDialog(Gui.this, "This is not a number.");
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode()==KeyEvent.VK_ENTER) {
                actionPerformed(null);
            }
        }

        @Override public void keyReleased(KeyEvent ke) {}
        @Override public void keyTyped(KeyEvent ke) {}

        public Double getTypedValue() {
            String typedText = mInputNumberField.getText().replace(',','.');
            try {
                mInputNumberField.setText("");
                return Double.parseDouble(typedText);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }

        public void calculateAverage() {
            try {
                double val = mComputer.calculate();
                mResultTextField.setText(String.format(
                    "Average: %.2f", val
                ));
            } catch (IllegalStateException ise) {
                JOptionPane.showMessageDialog(Gui.this, ise.getMessage());
            }
        }
    }
}