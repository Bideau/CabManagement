package com.example.quentin.myapplication.structures.map;


public class Area {

    // Name of the Area (East, Center, West, ...)
	private String name;
    // Object with the differents ArrayList of the map (Streets, Vertices, Bridges)
	private Carte map;
	
	public Area(){
		name = "Default";
		map=new Carte();
		
	}
	// ***************** GETTERS *******************//
	
	public String getName() {
		return name;
	}
	
	// **************** SETTERS ****************//
	public void setName(String _name) {
		this.name = _name;
	}

	public Carte getMap() {
		return map;
	}

	public void setMap(Carte map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Area [name=" + name + ", map=" + map + "]";
	}
	
}
