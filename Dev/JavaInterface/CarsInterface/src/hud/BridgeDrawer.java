package hud;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import structures.map.*;

public class BridgeDrawer {

	private Area area;
	
	private double x = 0.0;
	private double y = 0.0;
	
	private double VertexHeight = 0.0;
	private double VertexWidth = 0.0;
	
	private int xMaxPixels;
	private int yMaxPixels;

	public BridgeDrawer(){
		area = new Area();
	}

	public BridgeDrawer(Area _area, double _vertexWidth, double _vertexHeight, int xMaxPixels, int yMaxPixels){
		this.area = _area;
		this.VertexHeight = _vertexHeight;
		this.VertexWidth = _vertexWidth;
		this.xMaxPixels = xMaxPixels;
		this.yMaxPixels = yMaxPixels;
	}

	public void paintBridge(Graphics2D g2d){

		//************* Get and draw Vertices **************//
		for(Bridge object : this.area.getMap().getBridges()){
			
			x = (object.getFromVertice().getX() * xMaxPixels);
			y = object.getFromVertice().getY() * yMaxPixels;

			System.out.println("\nX : " + x + "\nY : " + y);

			// RECTANGLE		10.0,10.0,20.0,20.0
			// x - y
			// Longueur - Hauteur
			Rectangle2D cell = new Rectangle2D.Double(x-10,y-10,VertexWidth+20,VertexHeight+20);

			g2d.setColor(new Color(Integer.parseInt("00AAAA",16)));
			g2d.fill(cell);
		}
	}
}

