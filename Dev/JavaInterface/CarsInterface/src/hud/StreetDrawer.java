package hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import structures.map.*;

public class StreetDrawer {

	Area area;
	
	// Size of the vertex on the screen
	private double VertexHeight = 0.0;
	private double VertexWidth = 0.0;
	
	// Size of the screen. Use for Scaling.
	private int xMaxPixels;
	private int yMaxPixels;
	
	// Default constructor
	public StreetDrawer(){
		this.area = new Area();
	}
	
	// Overload constructor
	public StreetDrawer(Area _area, double _vertexWidth, double _vertexHeight, int xMaxPixels, int yMaxPixels){
		this.area = _area;
		this.VertexHeight = _vertexHeight;
		this.VertexWidth = _vertexWidth;
		this.xMaxPixels = xMaxPixels;
		this.yMaxPixels = yMaxPixels;
	}

	// Function for draw the streets on the screen
	public void paintStreets(Graphics g){

		//************* Get and draw Streets ***************//
		for(Street object : this.area.getMap().getStreets()){
			
			// Get the coordonates of the two vertex
			double origineX = object.getFirstVertice().getX();
			double origineY = object.getFirstVertice().getY();
			double destX = object.getSecondVertice().getX();
			double destY = object.getSecondVertice().getY();
			
			// Scaling with size of the screen
			origineX = (origineX * xMaxPixels)+(VertexWidth/2);
			origineY = (origineY * yMaxPixels)+(VertexHeight/2);
			destX = (destX * xMaxPixels)+(VertexWidth/2);
			destY = (destY * yMaxPixels)+(VertexHeight/2);
			
			// LINE
			// x Origine  -  y Origine
			// x Dest     -  y Dest
			//g.drawLine(15, 20, 500, 20);

			// Boost thickness
			((Graphics2D) g).setStroke(new BasicStroke(6));
			g.setColor(new Color(Integer.parseInt("0000FF",16)));

			// Draw Street
			g.drawLine((int)Math.round(origineX),
					(int)Math.round(origineY),
					(int)Math.round(destX),
					(int)Math.round(destY));
		}
	}
}
