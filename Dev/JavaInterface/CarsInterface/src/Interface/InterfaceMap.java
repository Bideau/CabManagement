package Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import structures.Area;
import structures.Coord;
import structures.Streets;
import structures.Vertice;

public class InterfaceMap extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int Width = 700;
	private static int Height = 500;
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel nothing;
	private JTextArea title;
	
	private Map map;
	private Area area;
	
	public InterfaceMap(){
		frame = new JFrame();
		mainPanel = new JPanel();
		nothing = new JPanel();
		title = new JTextArea();
		map = new Map();
		area = new Area();
	}
	
	public InterfaceMap(Area _area){
		frame = new JFrame();
		mainPanel = new JPanel();
		nothing = new JPanel();
		title = new JTextArea();
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
		title = new JTextArea();
		Font fontNothing = new Font(null, Font.BOLD,60);
		title.setFont(fontNothing);
		
		title.append(area.getMyName());
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
		double testX[] = { 10.0, 250.0, 10.0, 500.0};
		double testY[] = { 10.0, 250.0, 500.0, 500.0};
		//**************//
		
		Area east = new Area();
		ArrayList<Vertice> verticesList = new ArrayList<Vertice>();
		Streets street;
		Streets street2;
		Vertice vertice;
		Coord coord;
		
		east.setMyName("EAST");
		
		for(int i=0; i<testX.length; i++){
			vertice = new Vertice();
			coord = new Coord();
			
			coord.setX(testX[i]);
			coord.setY(testY[i]);
			
			//Point FixeVertice 
			vertice.setMyName(Integer.toString(i+1));
			vertice.setCoord(coord);
			
			verticesList.add(vertice);
			System.out.println("x Vertice : " + verticesList.get(i).getCoord().getX());
			System.out.println("name Vertice : " + verticesList.get(i).getName());
			
		}
		
		street = new Streets();
		street2 = new Streets();
		
		street.setFirstVertice(verticesList.get(0));
		street.setSecondVertice(verticesList.get(1));
		street2.setFirstVertice(verticesList.get(0));
		street2.setSecondVertice(verticesList.get(2));
		
		east.getStreetsList().add(street);
		east.getStreetsList().add(street2);

		east.setMyVerticesList(verticesList);
		
		InterfaceMap interface1 = new InterfaceMap(east);
		
		interface1.DrawInterface();
	}
}