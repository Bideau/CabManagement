package hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class CabDrawer {

	private static double CabWidth = 40;
	private static double CabHeight = 20;

	private double x;
	private double y;

	public CabDrawer(){
		this.x = 0.0;
		this.y = 0.0;
	}

	public CabDrawer(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void paintCab(Graphics2D g2d){
		// RECTANGLE		10.0,10.0,20.0,20.0
		// x - y
		// Longueur - Hauteur
		Rectangle2D cab = new Rectangle2D.Double(x,y,CabWidth,CabHeight);
		Rectangle2D _cab = new Rectangle2D.Double(x,y,CabWidth,CabHeight);

		g2d.setColor(new Color(Integer.parseInt("00FF00",16)));
		g2d.fill(cab);
		g2d.setColor(new Color(Integer.parseInt("FF0000",16)));
		g2d.draw(_cab);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
