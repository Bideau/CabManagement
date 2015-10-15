package Interface;

import structures.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Map extends JPanel {

	/**
	 * SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	private Area area;

	private static double VertexHeight = 30.0;
	private static double VertexWidth = 30.0;

	public Map(){}

	public Map(Area _Area){
		this.area = _Area;
	}


	public void paintComponent(Graphics g){

		super.paintComponent(g);
		Graphics2D g2d;
		g2d = (Graphics2D) g.create();

		double x = 0.0;
		double y = 0.0;

		//************* Get and draw Streets ***************//
		for(Streets object : this.area.getStreetsList()){

			double origineX = object.getFirstVertice().getCoord().getX();
			double origineY = object.getFirstVertice().getCoord().getY();;
			double destX = object.getSecondVertice().getCoord().getX();
			double destY = object.getSecondVertice().getCoord().getY();;

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

		//************* Get and draw Vertices **************//
		for(Vertice object : this.area.getVerticesList()){
			x = object.getCoord().getX();
			y = object.getCoord().getY();

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

		System.out.println("--------------------------");
		g2d.dispose();

	}

}
