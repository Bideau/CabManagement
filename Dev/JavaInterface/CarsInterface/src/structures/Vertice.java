package structures;

public class Vertice {

	private String name;
	private Coord point;
	
	// Default Constructor
	public Vertice(){
		this.name = "Default";
		point = new Coord();
	}
	
	// Overloaded constructor
	public Vertice(String _name, Coord _point){
		this.name = _name;
		this.point = _point;
	}

	// ****************** GETTERS ****************//
	public String getName() {
		return name;
	}
	
	public Coord getCoord() {
		return point;
	}

	// ***************** SETTERS ****************//
	public void setMyName(String _name) {
		this.name = _name;
	}
	
	public void setCoord(Coord _point) {
		this.point = _point;
	}
}
