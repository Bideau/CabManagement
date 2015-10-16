package structures.taxi;

public class CabInfo {
	
	private int odometer;
	private CabRequest destination;
	private LocVertex locNow;
	private LocVertex locNext;

	public CabInfo() {
		odometer=0;
		destination=new CabRequest();
		locNow=new LocVertex();
		locNext=new LocVertex();
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public CabRequest getDestination() {
		return destination;
	}

	public void setDestination(CabRequest destination) {
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

	@Override
	public String toString() {
		return "CabInfo [odometer=" + odometer + ", destination=" + destination
				+ ", locNow=" + locNow + ", locNext=" + locNext + "]";
	}

}
