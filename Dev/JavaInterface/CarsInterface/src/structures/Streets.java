package structures;

public class Streets {

	private String MyStreetName;
	private Vertice MyFirstVertice;
	private Vertice MySecondVertice;
	private boolean OneWay;
	
	// Default Constructor
	public Streets(){
		this.MyStreetName = "Default";
		this.MyFirstVertice = new Vertice();
		this.MySecondVertice = new Vertice();
		this.OneWay = false;
	}
	
	// Overload Constructor
	public Streets(String StreetName, Vertice FirstVertice, Vertice SecondVertice, boolean _OneWay){
		this.MyStreetName = StreetName;
		this.MyFirstVertice = FirstVertice;
		this.MySecondVertice = SecondVertice;
		this.OneWay = _OneWay;
	}


	//**************** GETTERS *******************//
	public String getMyStreetName() {
		return MyStreetName;
	}
	
	public Vertice getMyFirstVertice() {
		return MyFirstVertice;
	}

	public Vertice getMySecondVertice() {
		return MySecondVertice;
	}
	
	public boolean isOneWay() {
		return OneWay;
	}
	
	//***************** SETTERS ******************//
	public void setMyStreetName(String myStreetName) {
		MyStreetName = myStreetName;
	}
	
	public void setMyFirstVertice(Vertice myFirstVertice) {
		MyFirstVertice = myFirstVertice;
	}

	public void setMySecondVertice(Vertice mySecondVertice) {
		MySecondVertice = mySecondVertice;
	}
	
	public void setOneWay(boolean oneWay) {
		OneWay = oneWay;
	}
	
}
