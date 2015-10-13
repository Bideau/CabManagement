package structures;

public class Coord {

	private double x;
	private double y;
	
	public Coord(){
		x = 0.0;
		y = 0.0;
	}
	
	public Coord(double _x, double _y){
		this.x = _x;
		this.y = _y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
