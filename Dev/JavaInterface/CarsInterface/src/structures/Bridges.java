package structures;

public class Bridges {

	private Vertice FromVertice;
	private String ToArea;
	private String ToVertice;
	private double MyWeight;
	
	public Bridges(){
		this.FromVertice = new Vertice();
		this.ToArea ="";
		this.ToVertice = "";
		this.MyWeight = 0.0;
	}
	
	public Bridges(Vertice FromVertice, String ToArea, String ToVertice, double Weight){
		this.FromVertice = FromVertice;
		this.ToArea = ToArea;
		this.ToVertice = ToVertice;
		this.MyWeight = Weight;
	}

	// ******************* GETTERS **************//
	public Vertice getFromVertice() {
		return FromVertice;
	}

	public String getToArea() {
		return ToArea;
	}

	public String getToVertice() {
		return ToVertice;
	}

	public double getMyWeight() {
		return MyWeight;
	}

	// **************** SETTERS *****************//
	public void setFromVertice(Vertice fromVertice) {
		FromVertice = fromVertice;
	}
	
	public void setToArea(String toArea) {
		ToArea = toArea;
	}
	
	public void setToVertice(String toVertice) {
		ToVertice = toVertice;
	}
	
	public void setMyWeight(double myWeight) {
		MyWeight = myWeight;
	}
	
	
	
}
