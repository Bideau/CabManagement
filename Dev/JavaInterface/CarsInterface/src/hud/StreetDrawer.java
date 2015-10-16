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
	
	public StreetDrawer(){
		this.area = new Area();
	}
	
	public StreetDrawer(Area _area, double _vertexWidth, double _vertexHeight){
		this.area = _area;
		this.VertexHeight = _vertexHeight;
		this.VertexWidth = _vertexWidth;
	}

	public void paintStreets(Graphics g){

		//************* Get and draw Streets ***************//
		for(Street object : this.area.getMap().getStreets()){

			double origineX = object.getFirstVertice().getX();
			double origineY = object.getFirstVertice().getY();;
			double destX = object.getSecondVertice().getX();
			double destY = object.getSecondVertice().getY();;

			origineX = origineX + (VertexWidth/2);
			origineY = origineY + (VertexHeight/2);

			destX = destX + (VertexWidth/2);
			destY = destY + (VertexHeight/2);

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
