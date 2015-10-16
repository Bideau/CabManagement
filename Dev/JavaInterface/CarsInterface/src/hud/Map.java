package hud;

import structures.map.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Map extends JPanel {

	/**
	 * SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	private Area area;
	private StreetDrawer streetPainter;
	private VertexDrawer vertexPainter;
	
	private static double VertexHeight = 30.0;
	private static double VertexWidth = 30.0;

	public Map(){}

	public Map(Area _Area){
		this.area = _Area;
		this.streetPainter = new StreetDrawer(this.area, VertexWidth, VertexHeight);
		this.vertexPainter = new VertexDrawer(this.area, VertexWidth, VertexHeight);
	}

	public void paintComponent(Graphics g){

		super.paintComponent(g);
		Graphics2D g2d;
		g2d = (Graphics2D) g.create();
		
		// Draw the streets
		this.streetPainter.paintStreets(g2d);

		// Draw the vertices
		this.vertexPainter.paintVertices(g2d);

		System.out.println("--------------------------");
		g2d.dispose();

	}

}
