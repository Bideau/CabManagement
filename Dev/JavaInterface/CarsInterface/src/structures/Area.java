package structures;

import java.util.ArrayList;

public class Area {

	private String name;
	private ArrayList<Vertice> verticesList;
	private ArrayList<Streets> streetsList;
	private ArrayList<Bridges> bridgesList;
	private int width;
	private int height;
	
	public Area(){
		name = "Default";
		verticesList = new ArrayList<Vertice>();
		streetsList = new ArrayList<Streets>();
		bridgesList = new ArrayList<Bridges>();
		
	}

	// ***************** GETTERS *******************//
	
	public String getMyName() {
		return name;
	}
	
	public ArrayList<Vertice> getVerticesList() {
		return verticesList;
	}

	public ArrayList<Streets> getStreetsList() {
		return streetsList;
	}
	
	public ArrayList<Bridges> getBridgesList() {
		return bridgesList;
	}

	// **************** SETTERS ****************//
	public void setMyName(String _name) {
		this.name = _name;
	}

	public void setMyVerticesList(ArrayList<Vertice> _verticesList) {
		this.verticesList = _verticesList;
	}

	public void setMyStreetsList(ArrayList<Streets> _streetsList) {
		this.streetsList = _streetsList;
	}

	public void setMyBridgesList(ArrayList<Bridges> _bridgesList) {
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
