package com.example.quentin.myapplication.structures.map;

public class Dest {

	private String area;
	private String vertex;

    // Default constructor
	public Dest() {
		this.area ="";
		this.vertex = "";
	}

    @Override
    public String toString() {
        return "Dest [area=" + area + ", vertex=" + vertex + "]";
    }

    //*************** GETTERS *************//

    public String getArea() {
		return area;
	}

	public String getVertex() {
		return vertex;
	}

    //************** SETTERS **************//

	public void setArea(String _toArea) {
		this.area = _toArea;
	}
	
	public void setVertex(String _toVertice) {
		this.vertex = _toVertice;
	}
}
