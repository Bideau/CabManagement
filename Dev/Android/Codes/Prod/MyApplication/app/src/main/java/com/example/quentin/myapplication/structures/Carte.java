package com.example.quentin.myapplication.structures;

import java.util.ArrayList;

public class Carte {

	private ArrayList<Vertex> verticesList;
	private ArrayList<Street> streetsList;
	private ArrayList<Bridge> bridgesList;
	private MapSize weight;

	
	public Carte() {
		this.verticesList = new ArrayList<Vertex>();
		this.streetsList = new ArrayList<Street>();
		this.bridgesList = new ArrayList<Bridge>();
		this.weight=new MapSize();
	}

	public ArrayList<Vertex> getVertices() {
		return verticesList;
	}

	public void setVertices(ArrayList<Vertex> verticesList) {
		this.verticesList = verticesList;
	}

	public ArrayList<Street> getStreets() {
		return streetsList;
	}

	public void setStreets(ArrayList<Street> streetsList) {
		this.streetsList = streetsList;
	}

	public ArrayList<Bridge> getBridges() {
		return bridgesList;
	}

	public void setBridges(ArrayList<Bridge> bridgesList) {
		this.bridgesList = bridgesList;
	}

	public MapSize getWeight() {
		return weight;
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
