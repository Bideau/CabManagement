package structures.map;

import java.util.ArrayList;

public class Carte {

	// List of all the vertices of the area
	private ArrayList<Vertex> verticesList;
	// List of all the streets of the area
	private ArrayList<Street> streetsList;
	// List of all the bridges of the area
	private ArrayList<Bridge> bridgesList;
	// Size of the map (highest vertex in x and y)
	private MapSize weight;

	// Default constructor
	public Carte() {
		this.verticesList = new ArrayList<Vertex>();
		this.streetsList = new ArrayList<Street>();
		this.bridgesList = new ArrayList<Bridge>();
		this.weight=new MapSize();
	}

	//*************** GETTERS ***************//
	
	public ArrayList<Vertex> getVertices() {
		return verticesList;
	}
	
	public ArrayList<Street> getStreets() {
		return streetsList;
	}
	
	public ArrayList<Bridge> getBridges() {
		return bridgesList;
	}
	
	public MapSize getWeight() {
		return weight;
	}
	
	//************ SETTERS ************//

	public void setVertices(ArrayList<Vertex> verticesList) {
		this.verticesList = verticesList;
	}

	public void setStreets(ArrayList<Street> streetsList) {
		this.streetsList = streetsList;
	}

	public void setBridges(ArrayList<Bridge> bridgesList) {
		this.bridgesList = bridgesList;
	}

	public void setWeight(MapSize weight) {
		this.weight = weight;
	}
	

	public String toString() {
		return "Carte [verticesList=" + verticesList + ", streetsList="
				+ streetsList + ", bridgesList=" + bridgesList + ", weight="
				+ weight + "]";
	}	
}
