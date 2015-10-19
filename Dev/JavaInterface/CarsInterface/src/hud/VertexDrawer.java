package hud;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import structures.map.*;

public class VertexDrawer {

	private Area area;
	
	private double x = 0.0;
	private double y = 0.0;
	
	private double VertexHeight = 0.0;
	private double VertexWidth = 0.0;

	public VertexDrawer(){
		area = new Area();
	}

	public VertexDrawer(Area _area, double _vertexWidth, double _vertexHeight){
		this.area = _area;
		this.VertexHeight = _vertexHeight;
		this.VertexWidth = _vertexWidth;
	}

	public void paintVertices(Graphics2D g2d){

		//************* Get and draw Vertices **************//
		for(Vertex object : this.area.getMap().getVertices()){
			x = object.getX();
			y = object.getY();

			System.out.println("X : " + x + "\nY : " + y + "\nName : " + object.getName());

			// RECTANGLE		10.0,10.0,20.0,20.0
			// x - y
			// Longueur - Hauteur
			Rectangle2D cell = new Rectangle2D.Double(x,y,VertexWidth,VertexHeight);
			Rectangle2D cell2 = new Rectangle2D.Double(x,y,VertexWidth,VertexHeight);

			g2d.setColor(new Color(Integer.parseInt("000000",16)));
			g2d.fill(cell);
			g2d.setColor(new Color(Integer.parseInt("FF0000",16)));
			g2d.draw(cell2);
		}
	}
}

