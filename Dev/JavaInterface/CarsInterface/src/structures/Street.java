package structures;

public class Street {

	private String streetName;
	private Vertex firstVertice;
	private Vertex secondVertice;
	private boolean oneWay;
	
	// Default Constructor
	public Street(){
		this.streetName = "Default";
		this.firstVertice = new Vertex();
		this.secondVertice = new Vertex();
		this.oneWay = false;
	}
	
	// Overload Constructor
	public Street(String _streetName, Vertex _firstVertice, Vertex _secondVertice, boolean _oneWay){
		this.streetName = _streetName;
		this.firstVertice = _firstVertice;
		this.secondVertice = _secondVertice;
		this.oneWay = _oneWay;
	}


	//**************** GETTERS *******************//
	public String getStreetName() {
		return streetName;
	}
	
	public Vertex getFirstVertice() {
		return firstVertice;
	}

	public Vertex getSecondVertice() {
		return secondVertice;
	}
	
	public boolean isOneWay() {
		return oneWay;
	}
	
	//***************** SETTERS ******************//
	public void setStreetName(String _myStreetName) {
		streetName = _myStreetName;
	}
	
	public void setFirstVertice(Vertex _myFirstVertice) {
		firstVertice = _myFirstVertice;
	}

	public void setSecondVertice(Vertex _mySecondVertice) {
		secondVertice = _mySecondVertice;
	}
	
	public void setOneWay(boolean _oneWay) {
		oneWay = _oneWay;
	}
	
}
