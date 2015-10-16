package structures.taxi;

public class LocVertex {

	private String area;
	private String location;
	private String locationType;
	private int progression;
	
	public LocVertex() {
		area="";
		location="";
		locationType="";
		progression=0;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public int getProgression() {
		return progression;
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
