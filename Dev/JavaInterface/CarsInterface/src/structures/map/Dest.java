package structures.map;

public class Dest {
	private String area;
	private String vertex;

	public Dest() {
		this.area ="";
		this.vertex = "";
		// TODO Auto-generated constructor stub
	}

	public String getArea() {
		return area;
	}

	public String getVertex() {
		return vertex;
	}
	public void setArea(String _toArea) {
		this.area = _toArea;
	}
	
	public void setVertex(String _toVertice) {
		this.vertex = _toVertice;
	}

	@Override
	public String toString() {
		return "Dest [area=" + area + ", vertex=" + vertex + "]";
	}
}
