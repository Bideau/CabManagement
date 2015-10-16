package structures.taxi;

public class CabInfo {
	
	private int odometer;
	private String destination;
	private LocVertex locNow;
	private LocVertex locNext;

	public CabInfo() {
		odometer=0;
		destination="";
		locNow=new LocVertex();
		locNext=new LocVertex();
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocVertex getLocNow() {
		return locNow;
	}

	public void setLocNow(LocVertex locNow) {
		this.locNow = locNow;
	}

	public LocVertex getLocNext() {
		return locNext;
	}

	public void setLocNext(LocVertex locNext) {
		this.locNext = locNext;
	}

}
