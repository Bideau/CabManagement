package structures;

public class Bridges {

	private Vertice fromVertice;
	private String toArea;
	private String toVertice;
	private double weight;
	
	public Bridges(){
		this.fromVertice = new Vertice();
		this.toArea ="";
		this.toVertice = "";
		this.weight = 0.0;
	}
	
	public Bridges(Vertice _fromVertice, String _toArea, String _toVertice, double _weight){
		this.fromVertice = _fromVertice;
		this.toArea = _toArea;
		this.toVertice = _toVertice;
		this.weight = _weight;
	}

	// ******************* GETTERS **************//
	public Vertice getFromVertice() {
		return fromVertice;
	}

	public String getToArea() {
		return toArea;
	}

	public String getToVertice() {
		return toVertice;
	}

	public double getMyWeight() {
		return weight;
	}

	// **************** SETTERS *****************//
	public void setFromVertice(Vertice _fromVertice) {
		this.fromVertice = _fromVertice;
	}
	
	public void setToArea(String _toArea) {
		this.toArea = _toArea;
	}
	
	public void setToVertice(String _toVertice) {
		this.toVertice = _toVertice;
	}
	
	public void setMyWeight(double _myWeight) {
		this.weight = _myWeight;
	}
}
