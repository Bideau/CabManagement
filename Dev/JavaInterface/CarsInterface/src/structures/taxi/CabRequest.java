package structures.taxi;

public class CabRequest {
	
	private String area;
	private LocVertex location;
	
	// Default constructor
	public CabRequest() {
		area="";
		location=new LocVertex();
	}
	
	//************ GETTERS *************//
	
	public String getArea() {
		return area;
	}
	
	public LocVertex getLocation() {
		return location;
	}
	
	//***************** SETTERS ***************//
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public void setLocation(LocVertex location) {
		this.location = location;
	}
	
	
	@Override
	public String toString() {
		return "CabRequest [area=" + area + ", location=" + location + "]";
	}
}