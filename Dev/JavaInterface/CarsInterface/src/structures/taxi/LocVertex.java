package structures.taxi;

public class LocVertex {

	private String area;
	private String location;
	private String locationType;
	private int progression;
	
	// Default constructor
	public LocVertex() {
		area="";
		location="";
		locationType="";
		progression=1;
	}
	
	//******************* GETTERS *****************//
	
	public String getArea() {
		return area;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getLocationType() {
		return locationType;
	}
	
	public int getProgression() {
		return progression;
	}
	
	//***************** SETTERS *****************//
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	public void setProgression(int progression) {
		this.progression = progression;
	}
	
	
	@Override
	public String toString() {
		return "LocVertex [area=" + area + ", location=" + location
				+ ", locationType=" + locationType + ", progression="
				+ progression + "]";
	}
}