package hud;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


import structures.map.*;

public class VertexDrawer {

	private Area area;
	
	// Coordonate of every vertex on the arrayList
	private double x = 0.0;
	private double y = 0.0;
	
	// Size of the vertex on the screen
	private double VertexHeight = 0.0;
	private double VertexWidth = 0.0;
	
	// Value of size of the screen. Use for scaling
	private int xMaxPixels;
	private int yMaxPixels;

	// Default constructor
	public VertexDrawer(){
		area = new Area();
	}

	// Overload constructor
	public VertexDrawer(Area _area, double _vertexWidth, double _vertexHeight, int xMaxPixels, int yMaxPixels){
		this.area = _area;
		this.VertexHeight = _vertexHeight;
		this.VertexWidth = _vertexWidth;
		this.xMaxPixels = xMaxPixels;
		this.yMaxPixels = yMaxPixels;
	}

	// Function which draw all the vertices of the area
	public void paintVertices(Graphics2D g2d){

		//************* Get and draw Vertices **************//
		// Search all the vertices of the area
		for(Vertex object : this.area.getMap().getVertices()){
			
			// Get the coordonates of each vertex
			x = object.getX() * xMaxPixels;
			y = object.getY() * yMaxPixels;	        
			
			// RECTANGLE		10.0,10.0,20.0,20.0
			// x - y
			// Longueur - Hauteur
			Rectangle2D rect = new Rectangle2D.Double(x,y,VertexWidth,VertexHeight);
			Rectangle2D rect1 = new Rectangle2D.Double(x,y,VertexWidth,VertexHeight);

			g2d.setColor(new Color(Integer.parseInt("000000",16)));
			g2d.fill(rect);
			g2d.setColor(new Color(Integer.parseInt("FF0000",16)));
			g2d.draw(rect1);
		}
	}
}