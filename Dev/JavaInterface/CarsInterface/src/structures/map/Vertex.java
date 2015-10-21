package structures.map;

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
	public Vertex(String name, double x, double y){
		this.name = name;
		this.x = x;
		this.y = y;
	}

	// ****************** GETTERS ****************//
	public String getName() {
		return name;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	

	// ***************** SETTERS ****************//
	public void setName(String name) {
		this.name = name;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	
	@Override
	public String toString() {
		return "Vertex [name=" + name + ", x=" + x + ", y=" + y + "]";
	}
}
