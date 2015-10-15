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

	private Area MyArea;
	
	private double TownHeight = 30.0;
	private double TownWidth = 30.0;

	public Map(){}

	public Map(Area _Area){
		this.MyArea = _Area;
	}


	public void paintComponent(Graphics g){

		super.paintComponent(g);
		Graphics2D g2d;
		g2d = (Graphics2D) g.create();

		double x = 0.0;
		double y = 0.0;

		// Recuperation et affichage des Vertices (Villes)
		for(Vertice object : this.MyArea.getMyVerticesList()){
			x = object.getCoord().getX();
			y = object.getCoord().getY();

			System.out.println("X : " + x + "\nY : " + y + "\nName : " + object.getMyName());

			// RECTANGLE		10.0,10.0,20.0,20.0
			// x - y
			// Longueur - Hauteur
			Rectangle2D cell = new Rectangle2D.Double(x,y,TownWidth,TownHeight);
			Rectangle2D cell2 = new Rectangle2D.Double(x,y,TownWidth,TownHeight);

			g2d.setColor(new Color(Integer.parseInt("000000",16)));
			g2d.fill(cell);
			g2d.setColor(new Color(Integer.parseInt("FF0000",16)));
			g2d.draw(cell2);

		}

		// Recuperation et affichage des Streets
		for(Streets object : this.MyArea.getMyStreetsList()){
			
			double OrigineX = object.getMyFirstVertice().getCoord().getX();
			double OrigineY = object.getMyFirstVertice().getCoord().getY();;
			double DestX = object.getMySecondVertice().getCoord().getX();
			double DestY = object.getMySecondVertice().getCoord().getY();;
			
			//
			OrigineX = OrigineX + (TownWidth/2);
			OrigineY = OrigineY + (TownHeight/2);
			
			DestX = DestX + (TownWidth/2);
			DestY = DestY + (TownHeight/2);
			
			// LINE
			// x Origine  -  y Origine
			// x Dest     -  y Dest
			//g.drawLine(15, 20, 500, 20);
			
			// Boost thickness
			((Graphics2D) g).setStroke(new BasicStroke(3));
			g.setColor(new Color(Integer.parseInt("0000FF",16)));
			
			// Draw Streets (Horizontale et verticale)
			if(OrigineY == DestY){
				if(OrigineX < DestX){
					g.drawLine((int)Math.round(OrigineX)+(int)Math.round((TownWidth/2)),
							   (int)Math.round(OrigineY),
							   (int)Math.round(DestX)-(int)Math.round((TownWidth/2)),
							   (int)Math.round(DestY));
				}else{
					g.drawLine((int)Math.round(OrigineX)-(int)Math.round((TownWidth/2)),
							   (int)Math.round(OrigineY),
							   (int)Math.round(DestX)+(int)Math.round((TownWidth/2)),
							   (int)Math.round(DestY));
				}
			}else{
				if(OrigineY < DestY){
					g.drawLine((int)Math.round(OrigineX),
							   (int)Math.round(OrigineY)+(int)Math.round((TownHeight/2)),
							   (int)Math.round(DestX),
							   (int)Math.round(DestY)-(int)Math.round((TownHeight/2)));
				}else{
					g.drawLine((int)Math.round(OrigineX),
							   (int)Math.round(OrigineY)-(int)Math.round((TownHeight/2)),
							   (int)Math.round(DestX),
							   (int)Math.round(DestY)+(int)Math.round((TownHeight/2)));
				}
			}
		}

		System.out.println("--------------------------");
		g2d.dispose();

	}

}
