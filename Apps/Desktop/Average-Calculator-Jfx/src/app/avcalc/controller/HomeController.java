package app.avcalc.controller;

import com.jjfx.context.Context;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HomeController extends VBox {
    @FXML
    private TextField valueField;

    @FXML
    private Button putButton;

    @FXML
    private Label outputLabel;

    private double sum;
    private int count;
    private final Context context;
    
    public HomeController(Context context) {
    	this.context = context;
    }

    @FXML
    public void handlePutValue() {
        try {
            sum += Double.parseDouble(valueField.getText());
            count++;
            double avg = sum/(double)count;
            outputLabel.setText(
                String.format("Average: %.2f", avg)
            );
            valueField.clear();
        } catch (NumberFormatException nfe) {
            IO.println(nfe);
        }
    }
}
