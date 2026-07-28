package app.matrix;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.finder.Matrix;

public final class GraphicalMatrix<T> extends JPanel implements Matrix<T> {
	private final T[][] mDataMatrix;
	private final JTextField[][] mGraphicsMatrix;

	public GraphicalMatrix(T[][] matrix) {
		this.mDataMatrix = matrix;
		this.mGraphicsMatrix = new JTextField[mDataMatrix.length][mDataMatrix[0].length];
		setLayout(new GridLayout(matrix.length, matrix[0].length));
		initGraphics();
	}
	
	public T get(int r, int c) {
		final JComponent panel=mGraphicsMatrix[r][c];
		panel.setBackground(Color.RED);
		panel.paintImmediately(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight());
		paintImmediately(getX(), getY(), getWidth(), getHeight());
		
		try {
			Thread.sleep(40);
			panel.setBackground(Color.WHITE);
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panel.paintImmediately(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight());
			paintImmediately(getX(), getY(), getWidth(), getHeight());
		} catch (InterruptedException ie) {
			// ignore
		}
		return mDataMatrix[r][c];
	}
	
	public void highlightLine(Point p1, Point p2) {
		if(p1.x!=p2.x&&p1.y!=p2.y)
			// Throw exception if given line is not straight.
			throw new IllegalArgumentException();
		
		boolean highlightHorizontal=p1.y!=p2.y;
		if(highlightHorizontal) {
			for(int y=Math.min(p1.y, p2.y); y<Math.max(p1.y, p2.y); y++) {
				JComponent comp=mGraphicsMatrix[p1.x][y];
				comp.setBackground(Color.RED);
			}
		} else {
			for(int x=Math.min(p1.x, p2.x); x<Math.max(p1.x, p2.x); x++) {
				JComponent comp=mGraphicsMatrix[x][p1.y];
				comp.setBackground(Color.RED);
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			// ignore
		}
		if(highlightHorizontal) {
			for(int y=Math.min(p1.y, p2.y); y<Math.max(p1.y, p2.y); y++) {
				JComponent comp=mGraphicsMatrix[p1.x][y];
				comp.setBackground(Color.WHITE);
				comp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		} else {
			for(int x=Math.min(p1.x, p2.x); x<Math.max(p1.x, p2.x); x++) {
				JComponent comp=mGraphicsMatrix[x][p1.y];
				comp.setBackground(Color.WHITE);
				comp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
	}
	
	private void initGraphics() {
		for(int i=0; i<mDataMatrix.length; i++) {
			for(int j=0; j<mDataMatrix[i].length; j++) {
				JTextField field=new JTextField();
				field.setText(mDataMatrix[i][j].toString());
				field.setEditable(false);
				field.setHorizontalAlignment(JTextField.CENTER);
				add(field);
				mGraphicsMatrix[i][j]=field;
			}
		}
	}

	@Override
	public int rows() {
		return mDataMatrix.length;
	}

	@Override
	public int cols() {
		return mDataMatrix[0].length;
	}

	@Override
	public void forEach(Consumer<T> consumer) {
		for(T[] row: mDataMatrix) {
			for(T e: row) {
				consumer.accept(e);
			}
		}
	}

}
