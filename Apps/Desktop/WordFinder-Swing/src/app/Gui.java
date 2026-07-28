package app;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.finder.Finder;
import app.matrix.GraphicalMatrix;

public class Gui extends JFrame {
	// gui
	private final JPanel jpanel=new JPanel();
	private final JPanel innerPanel=new JPanel();
	private final JTextArea resultArea=new JTextArea();
	private final JTextField inputField=new JTextField(5);
	private final JButton jbutton=new JButton("Find Word");
	
	// logic & gui
	private final GraphicalMatrix<Character> gMatrix;
	private final Finder finder;

	public Gui(GraphicalMatrix<Character> matrix) {
		// init obj
		this.gMatrix=matrix;
		this.finder=new Finder(matrix);
		
		initGui();
	}
	
	private void initGui() {
		this.setLayout(new GridLayout(3, 1));
		
		jpanel.add(gMatrix);
		jpanel.add(resultArea);
		innerPanel.add(inputField);
		innerPanel.add(jbutton);
		jbutton.addActionListener(new ButtonListener());
		jpanel.add(innerPanel);
		this.add(jpanel);
		
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			find(inputField.getText());
		}
		private void find(String txt) {
			resultArea.append(txt+": "+finder.find(txt)+'\n');
		}
	}
}
