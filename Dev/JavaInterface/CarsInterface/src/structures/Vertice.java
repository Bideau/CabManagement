package structures;

public class Vertice {

	private String MyName;
	private Coord MyPoint;
	
	// Default Constructor
	public Vertice(){
		this.MyName = "Default";
		MyPoint = new Coord();
	}
	
	// Overloaded constructor
	public Vertice(String Name, Coord ParameterArrayList){
		this.MyName = Name;
		this.MyPoint = ParameterArrayList;
	}

	// ****************** GETTERS ****************//
	public String getMyName() {
		return MyName;
	}
	
	public Coord getMyPoint() {
		return MyPoint;
	}

	// ***************** SETTERS ****************//
	public void setMyName(String myName) {
		MyName = myName;
	}
	
	public void setMyPoint(Coord myPoint) {
		MyPoint = myPoint;
	}
	
}
