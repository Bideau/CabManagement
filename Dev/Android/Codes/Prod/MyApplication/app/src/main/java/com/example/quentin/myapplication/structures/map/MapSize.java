package com.example.quentin.myapplication.structures.map;

public class MapSize {

    // Height and width of the map
	private int w;
	private int h;

    // Default Constructor
	public MapSize() {
		w=0;
		h=0;
	}

    //***************** GETTERS ****************//

	public int getW() {
		return w;
	}

    public int getH() {
        return h;
    }

    //*************** SETTERS *****************//

    public void setW(int width) {
        this.w = width;
    }

	public void setH(int height) {
		this.h = height;
	}
}
