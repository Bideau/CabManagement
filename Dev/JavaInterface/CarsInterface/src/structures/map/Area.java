package structures.map;


public class Area {

	// Name of the Area (East, Center, West, ...)
	private String name;
	// Object with the differents ArrayList of the map (Streets, Vertices, Bridges)
	private Carte map;
	
	// Default Constructor
	public Area(){
		name = "Default";
		map=new Carte();
		
	}
	// ***************** GETTERS *******************//
	
	public String getName() {
		return name;
	}
	
	public Carte getMap() {
		return map;
	}
	
	// **************** SETTERS ****************//
	
	public void setName(String _name) {
		this.name = _name;
	}

	public void setMap(Carte map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Area [name=" + name + ", map=" + map + "]";
	}
	
}
