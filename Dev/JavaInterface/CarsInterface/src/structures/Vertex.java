package structures;

public class Vertex {

	private String name;
	private double x;
	private double y;
	
	// Default Constructor
	public Vertex(){
		this.name = "Default";
		this.x = 0.0;
		this.y = 0.0;
	}
	
	// Overloaded constructor
	public Vertex(String _name, double _x, double _y){
		this.name = _name;
		this.x = _x;
		this.y = _y;
	}

	// ****************** GETTERS ****************//
	public String getName() {
		return name;
	}
	

	// ***************** SETTERS ****************//
	public void setName(String _name) {
		this.name = _name;
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

	@Override
	public String toString() {
		return "Vertex [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
	

}
