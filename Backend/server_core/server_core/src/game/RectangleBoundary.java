package game;

import java.util.Collection;
import java.util.LinkedList;

public class RectangleBoundary {
	private Position upperRight;
	private Position lowerLeft;
	
	public RectangleBoundary(Position upperRight, Position lowerLeft) {
		assert (upperRight.getDungeon() == lowerLeft.getDungeon());
		if (lowerLeft.getX() <= upperRight.getX() && lowerLeft.getY() <= upperRight.getY()) {
			this.upperRight = upperRight;
			this.lowerLeft = lowerLeft;
		} else {
			this.upperRight = lowerLeft;
			this.lowerLeft = upperRight;
		}
	}
	
	public RectangleBoundary(Position location) {
		upperRight = location;
		lowerLeft = location;
	}

	public Collection<Position> getBetween() {
		int x0 = lowerLeft.getX(), x1 = upperRight.getX();
		int y0 = lowerLeft.getY(), y1 = upperRight.getY();
		Collection<Position> coordinates = new LinkedList<Position>();
		for (; x0 <= x1; x0++ ) {
			for (y0 = lowerLeft.getY(); y0 <= y1; y0++) {
				coordinates.add(new Position(x0,y0, upperRight.getDungeon()));
			}
		}
		return coordinates;
	}
	
	public int getUnitChange(RectangleBoundary bound) {
		int deltaX = bound.upperRight.getX() - upperRight.getX();
		int deltaY = bound.upperRight.getY() - upperRight.getY();
		return Math.abs(deltaX) + Math.abs(deltaY);
	}
	
	/*
	 * Get the coordinates that are not in this area, but are in other's area
	 */
	public Collection<Position> getDifference(RectangleBoundary other) {
		Collection<Position> difference = other.getBetween();
		difference.removeAll(this.getBetween());
		return difference;
	}
	
	/**
	 * Determines if the given position falls within this rectangle boundary
	 * @param position
	 * @return true if the position is within bounds, false otherwise
	 */
	public boolean isAt(Position position) {
		//TODO
		return true;
	}
	
	public Position getUpperRight() {return upperRight;}
	public Position getLowerLeft() {return lowerLeft;}
	
	public long getDungeon() {
		return upperRight.getDungeon();
	}
	
	@Override
	public String toString() {
		return "lowerLeft: " + lowerLeft.toString() + " " +
				"upperRight: " + upperRight.toString() + " ";
	}
}
