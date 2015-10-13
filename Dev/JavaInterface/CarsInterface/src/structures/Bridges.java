package structures;

public class Bridges {

	private Vertice FromVertice;
	private Area ToArea;
	private Vertice ToVertice;
	private double MyWeight;
	
	public Bridges(){
		this.FromVertice = new Vertice();
		this.ToArea = new  Area();
		this.ToVertice = new Vertice();
		this.MyWeight = 0.0;
	}
	
	public Bridges(Vertice FromVertice, Area ToArea, Vertice ToVertice, double Weight){
		this.FromVertice = FromVertice;
		this.ToArea = ToArea;
		this.ToVertice = ToVertice;
		this.MyWeight = Weight;
	}

	// ******************* GETTERS **************//
	public Vertice getFromVertice() {
		return FromVertice;
	}

	public Area getToArea() {
		return ToArea;
	}

	public Vertice getToVertice() {
		return ToVertice;
	}

	public double getMyWeight() {
		return MyWeight;
	}

	// **************** SETTERS *****************//
	public void setFromVertice(Vertice fromVertice) {
		FromVertice = fromVertice;
	}
	
	public void setToArea(Area toArea) {
		ToArea = toArea;
	}
	
	public void setToVertice(Vertice toVertice) {
		ToVertice = toVertice;
	}
	
	public void setMyWeight(double myWeight) {
		MyWeight = myWeight;
	}
	
	
	
}
