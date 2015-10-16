package structures;


public class Bridge {

	private String from;
	private Dest to;
	private Vertex fromVertice;
	private double weight;
	
	public Bridge(){
		this.fromVertice = new Vertex();
		this.to=new Dest();
		this.weight = 0.0;
		this.from="";

	}
	
	public Bridge(Vertex _fromVertice, double _weight){
		this.fromVertice = _fromVertice;
		this.weight = _weight;

	}

	// ******************* GETTERS **************//
	public Vertex getFromVertice() {
		return fromVertice;
	}



	public double getMyWeight() {
		return weight;
	}

	// **************** SETTERS *****************//
	public void setFromVertice(Vertex _fromVertice) {
		this.fromVertice = _fromVertice;
	}
	

	
	public void setMyWeight(double _myWeight) {
		this.weight = _myWeight;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}


	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Dest getTo() {
		return to;
	}

	public void setTo(Dest to) {
		this.to = to;
	}
}
