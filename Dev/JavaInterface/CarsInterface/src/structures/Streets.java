package structures;

public class Streets {

	private String streetName;
	private Vertice firstVertice;
	private Vertice secondVertice;
	private boolean oneWay;
	
	// Default Constructor
	public Streets(){
		this.streetName = "Default";
		this.firstVertice = new Vertice();
		this.secondVertice = new Vertice();
		this.oneWay = false;
	}
	
	// Overload Constructor
	public Streets(String _streetName, Vertice _firstVertice, Vertice _secondVertice, boolean _oneWay){
		this.streetName = _streetName;
		this.firstVertice = _firstVertice;
		this.secondVertice = _secondVertice;
		this.oneWay = _oneWay;
	}


	//**************** GETTERS *******************//
	public String getStreetName() {
		return streetName;
	}
	
	public Vertice getFirstVertice() {
		return firstVertice;
	}

	public Vertice getSecondVertice() {
		return secondVertice;
	}
	
	public boolean isOneWay() {
		return oneWay;
	}
	
	//***************** SETTERS ******************//
	public void setStreetName(String _myStreetName) {
		streetName = _myStreetName;
	}
	
	public void setFirstVertice(Vertice _myFirstVertice) {
		firstVertice = _myFirstVertice;
	}

	public void setSecondVertice(Vertice _mySecondVertice) {
		secondVertice = _mySecondVertice;
	}
	
	public void setOneWay(boolean _oneWay) {
		oneWay = _oneWay;
	}
	
}
