package structures.map;

import java.util.ArrayList;

public class Street {

	private String name;
	private Vertex firstVertice;
	private Vertex secondVertice;
	private ArrayList<String> path;
	private boolean oneway;
	
	// Default Constructor
	public Street(){
		this.name = "Default";
		this.firstVertice = new Vertex();
		this.secondVertice = new Vertex();
		this.oneway = false;
		this.path=new ArrayList<String>();
	}
	
	// Overload Constructor
	public Street(String _streetName, Vertex _firstVertice, Vertex _secondVertice, boolean _oneWay){
		this.name = _streetName;
		this.firstVertice = _firstVertice;
		this.secondVertice = _secondVertice;
		this.oneway = _oneWay;
	}


	//**************** GETTERS *******************//
	public String getName() {
		return name;
	}
	
	public Vertex getFirstVertice() {
		return firstVertice;
	}

	public Vertex getSecondVertice() {
		return secondVertice;
	}
	
	public boolean isOneway() {
		return oneway;
	}
	
	//***************** SETTERS ******************//
	public void setName(String _myStreetName) {
		name = _myStreetName;
	}
	
	public void setFirstVertice(Vertex _myFirstVertice) {
		firstVertice = _myFirstVertice;
	}

	public void setSecondVertice(Vertex _mySecondVertice) {
		secondVertice = _mySecondVertice;
	}
	
	public void setOneway(boolean _oneWay) {
		oneway = _oneWay;
	}

	public ArrayList<String> getPath() {
		return path;
	}

	public void setPath(ArrayList<String> path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Street [name=" + name + ", firstVertice=" + firstVertice
				+ ", secondVertice=" + secondVertice + ", path=" + path
				+ ", oneway=" + oneway + "]";
	}
	
}
