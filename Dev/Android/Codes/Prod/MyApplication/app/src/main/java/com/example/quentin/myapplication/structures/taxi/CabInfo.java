package com.example.quentin.myapplication.structures.taxi;

public class CabInfo {

    private int odometer;
    private CabRequest destination;
    private LocVertex locNow;
    private LocVertex locNext;

    // Default constructor
    public CabInfo() {
        odometer=0;
        destination=new CabRequest();
        locNow=new LocVertex();
        locNext=new LocVertex();
    }

    @Override
    public String toString() {
        return "CabInfo [odometer=" + odometer + ", destination=" + destination
                + ", locNow=" + locNow + ", locNext=" + locNext + "]";
    }

    //***************** GETTERS **************//

    public int getOdometer() {
        return odometer;
    }

    public CabRequest getDestination() {
        return destination;
    }

    public LocVertex getLocNow() {
        return locNow;
    }

    public LocVertex getLocNext() {
        return locNext;
    }

    //************** SETTERS **************//

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setDestination(CabRequest destination) {
        this.destination = destination;
    }

    public void setLocNow(LocVertex locNow) {
        this.locNow = locNow;
    }

    public void setLocNext(LocVertex locNext) {
        this.locNext = locNext;
    }
}
