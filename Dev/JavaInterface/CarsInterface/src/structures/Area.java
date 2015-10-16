package structures;

import java.util.ArrayList;

public class Area {

	private String name;
	private ArrayList<Vertex> verticesList;
	private ArrayList<Street> streetsList;
	private ArrayList<Bridge> bridgesList;
	private int width;
	private int height;
	
	public Area(){
		name = "Default";
		verticesList = new ArrayList<Vertex>();
		streetsList = new ArrayList<Street>();
		bridgesList = new ArrayList<Bridge>();
		
	}

	// ***************** GETTERS *******************//
	
	public String getMyName() {
		return name;
	}
	
	public ArrayList<Vertex> getVerticesList() {
		return verticesList;
	}

	public ArrayList<Street> getStreetsList() {
		return streetsList;
	}
	
	public ArrayList<Bridge> getBridgesList() {
		return bridgesList;
	}

	// **************** SETTERS ****************//
	public void setMyName(String _name) {
		this.name = _name;
	}

	public void setMyVerticesList(ArrayList<Vertex> _verticesList) {
		this.verticesList = _verticesList;
	}

	public void setMyStreetsList(ArrayList<Street> _streetsList) {
		this.streetsList = _streetsList;
	}

	public void setMyBridgesList(ArrayList<Bridge> _bridgesList) {
		this.bridgesList = _bridgesList;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int _width) {
		this.width = _width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int _height) {
		this.height = _height;
	}	
	
}
