package hud;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import structures.map.*;

public class InterfaceMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int Width = 700;
	private static int Height = 500;
	private static int XOffsetPixels = 10;
	private static int XMaxPixels = 1300;
	private static int YOffsetPixels = 0;
	private static int YMaxPixels = 550;
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel nothing;
	private JTextArea title;
	private Component mouseListenerArea;
	
	private Map map;
	private Area area;
	
	public InterfaceMap(){
		frame = new JFrame();
		mainPanel = new JPanel();
		nothing = new JPanel();
		title = new JTextArea();
		mouseListenerArea = new JTextArea();
		map = new Map();
		area = new Area();
	}
	
	public InterfaceMap(Area _area){
		frame = new JFrame();
		mainPanel = new JPanel();
		nothing = new JPanel();
		title = new JTextArea();
		mouseListenerArea = new JTextArea();
		area = _area;
		map = new Map(area);
		System.out.println("Overload Constructor");
	}
	
	public void DrawInterface(){
		
		frame.setSize(Width,Height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		mainPanel.setLayout(new BorderLayout());
		
		nothing = new JPanel();
		nothing.setLayout(new FlowLayout());
		Font fontNothing = new Font(null, Font.BOLD,60);
		title.setFont(fontNothing);
		mouseListenerArea.setFont(fontNothing);
		
		title.append(area.getName());
		nothing.add(title);		
		
		mainPanel.add(nothing,BorderLayout.NORTH);
		
		mainPanel.add(map,BorderLayout.CENTER);
		
		map.setVisible(true);
		
		mainPanel.repaint();
		
		title.setEditable(false);
		frame.setResizable(true);
		frame.getContentPane().add(mainPanel);
		mainPanel.setVisible(true);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		
		//**** TEST ****//
		double testX[] = { 0.0, 0.0, 100.0, 100.0};
		double testY[] = { 0.0, 100.0, 0.0, 100.0};
		//**************//
		
		Area east = new Area();
		ArrayList<Vertex> verticesList = new ArrayList<Vertex>();
		Street street;
		Street street2;
		
		east.setName("WEST");
		
		for(int i=0; i<testX.length; i++){
			Vertex vertice = new Vertex();
			
			testX[i] = ((testX[i]*XMaxPixels)/100) + XOffsetPixels;
			testY[i] = ((testY[i]*YMaxPixels)/100) + YOffsetPixels;
			
			vertice.setX(testX[i]);
			vertice.setY(testY[i]);
			
			//Point FixeVertice 
			vertice.setName(Integer.toString(i+1));
			
			verticesList.add(vertice);
			System.out.println("x Vertice : " + verticesList.get(i).getX());
			System.out.println("name Vertice : " + verticesList.get(i).getName());
		}
		
		street = new Street();
		street2 = new Street();
		
		street.setFirstVertice(verticesList.get(0));
		street.setSecondVertice(verticesList.get(1));
		street2.setFirstVertice(verticesList.get(0));
		street2.setSecondVertice(verticesList.get(2));
		
		east.getMap().getStreets().add(street);
		east.getMap().getStreets().add(street2);

		east.getMap().setVertices(verticesList);
		
		InterfaceMap interface1 = new InterfaceMap(east);
		
		interface1.DrawInterface();
	}
}
