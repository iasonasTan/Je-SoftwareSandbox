package app.finder;

import java.awt.Point;

import app.matrix.GraphicalMatrix;

public class Finder {
	private final GraphicalMatrix<Character> matrix;

	public Finder(GraphicalMatrix<Character> matrix) {
		this.matrix=matrix;
	}
	
	public PathPoint find(String word) { 
		if(word.isEmpty())
			return null;
		char initial=word.charAt(0);
		// iterate through matrix
		for(int i=0; i<matrix.rows(); i++) {
			for(int j=0; j<matrix.cols(); j++) {
				char c=matrix.get(i, j);
				System.out.println("Checking current char "+c+"...");
				if(c==initial) {
					System.out.println("Characters match, checking other characters...");
					SearchResults sr=checkInitial(i, j, word, Direction.LEFT);
					if(sr.find) {
						matrix.highlightLine(new Point(i, j), sr.endPoint);
						return new PathPoint(new Point(i, j), sr.endPoint);
					}
					sr=checkInitial(i, j, word, Direction.UP);
					if(sr.find) {
						matrix.highlightLine(new Point(i, j), sr.endPoint);
						return new PathPoint(new Point(i, j), sr.endPoint);
					}
					sr=checkInitial(i, j, word, Direction.RIGHT);
					if(sr.find) {
						matrix.highlightLine(new Point(i, j), sr.endPoint);
						return new PathPoint(new Point(i, j), sr.endPoint);
					}
					sr=checkInitial(i, j, word, Direction.DOWN);
					if(sr.find) {
						matrix.highlightLine(new Point(i, j), sr.endPoint);
						return new PathPoint(new Point(i, j), sr.endPoint);
					}
				} else {
					System.out.println("Characters don't match, checking next character...");
				}
			}
		}
		return null;
	}
	
	private SearchResults checkInitial(int i, int j, final String word, final Direction direction) {
		SearchResults falseResults=new SearchResults(false, new Point());
		int wordIdx=0;
		for(; wordIdx<word.length(); wordIdx++) {
			System.out.println("Matrix char:"+matrix.get(i, j)+", Required char:"+word.charAt(wordIdx));
			if(word.charAt(wordIdx)!=matrix.get(i, j)) {
				System.out.println("Chars don't match, returning false results.");
				return falseResults;
			}
			if(direction==Direction.UP&&i!=0) i--;
			else if (direction==Direction.DOWN&&i!=matrix.getHeight()) i++;
			else if (direction==Direction.LEFT&&j!=0) j--;
			else if (direction==Direction.RIGHT&&j!=matrix.getWidth()) j++;
		}
		SearchResults trueResults=new SearchResults(true, new Point(i, j));
		return trueResults;
	}
	
	private enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT;
	}

	private final class SearchResults {
		public final boolean find;
		public final Point endPoint;
		
		public SearchResults(boolean find, Point endPoint) {
			this.find=find;
			this.endPoint=endPoint;
		}
		
		@Override
		public String toString() {
			return getClass().getName()+"["+find+", "+endPoint+"]";
		}
	}
	
	public final class PathPoint {
		public final Point start;
		public final Point end;
	
		public PathPoint(Point start, Point end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String toString() {
			return getClass().getName()+"["+start+", "+end.toString()+"]";
		}
	}

}
