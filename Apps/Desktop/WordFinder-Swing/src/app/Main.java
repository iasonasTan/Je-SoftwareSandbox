package app;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import app.matrix.GraphicalMatrix;

public class Main {
	public static void main(String[] args) {
		getMatrix();
	}

	private static void getMatrix() {
		JTextArea matrixInput=new JTextArea();
		JButton button=new JButton();
		JPanel panel=new JPanel(new GridLayout(1,2));
		panel.add(matrixInput);
		panel.add(button);
		JFrame frame=new JFrame();
		frame.setContentPane(panel);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		button.addActionListener(ae -> {
			char[] characters=matrixInput.getText().toCharArray();
			int mW=0, mH=0;
			boolean firstLine=true;
			for(char c: characters) {
				if(c=='\n') {
					mH++;
					firstLine=false;
				}
				if(firstLine)
					mW++;
			}
			Character[][] matrix=new Character[mH][mW];
			int line=0;
			int line_idx=0;
			for(int i=0; i<characters.length; i++) {
				if(characters[i]=='\n') {
					line++;
					line_idx=0;
					continue;
				}
				if(line < matrix.length && line_idx < matrix[0].length) { 
					matrix[line]
							[line_idx]=characters[i];
				}
				line_idx++;
			}
			new Gui(new GraphicalMatrix<Character>(matrix));
		});
	}
	
	
}
