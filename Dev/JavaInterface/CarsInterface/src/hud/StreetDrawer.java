package hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import structures.map.*;

public class StreetDrawer {

	Area area;
	
	private double VertexHeight = 0.0;
	private double VertexWidth = 0.0;
	
	private int xMaxPixels;
	private int yMaxPixels;
	
	public StreetDrawer(){
		this.area = new Area();
	}
	
	public StreetDrawer(Area _area, double _vertexWidth, double _vertexHeight, int xMaxPixels, int yMaxPixels){
		this.area = _area;
		this.VertexHeight = _vertexHeight;
		this.VertexWidth = _vertexWidth;
		this.xMaxPixels = xMaxPixels;
		this.yMaxPixels = yMaxPixels;
	}

	public void paintStreets(Graphics g){

		//************* Get and draw Streets ***************//
		for(Street object : this.area.getMap().getStreets()){
			System.out.println(object);
			double origineX = object.getFirstVertice().getX();
			double origineY = object.getFirstVertice().getY();
			double destX = object.getSecondVertice().getX();
			double destY = object.getSecondVertice().getY();

			System.out.println("origin : "+origineX+" "+origineY);
			System.out.println("dest : "+destX + " "+destY);
			
			origineX = (origineX * xMaxPixels)+(VertexWidth/2);
			origineY = (origineY * yMaxPixels)+(VertexHeight/2);

			destX = (destX * xMaxPixels)+(VertexWidth/2);
			destY = (destY * yMaxPixels)+(VertexHeight/2);
			
			
			System.out.println("origin : "+origineX+" "+origineY);
			System.out.println("dest : "+destX + " "+destY);
			// LINE
			// x Origine  -  y Origine
			// x Dest     -  y Dest
			//g.drawLine(15, 20, 500, 20);

			// Boost thickness
			((Graphics2D) g).setStroke(new BasicStroke(6));
			g.setColor(new Color(Integer.parseInt("0000FF",16)));

			System.out.println("LINE O : " + (int)Math.round(origineX) + "    " + (int)Math.round(origineY));
			System.out.println("LINE D : " + (int)Math.round(destX) + "    " + (int)Math.round(destY));
			
			// Draw Street
			g.drawLine((int)Math.round(origineX),
					(int)Math.round(origineY),
					(int)Math.round(destX),
					(int)Math.round(destY));
		}

	}

}
