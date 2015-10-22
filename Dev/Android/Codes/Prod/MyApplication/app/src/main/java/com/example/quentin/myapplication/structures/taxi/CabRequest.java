package com.example.quentin.myapplication.structures.taxi;

public class CabRequest {

    private String area;
    private LocVertex location;

    // Default constructor
    public CabRequest() {
        area="";
        location=new LocVertex();
    }

    @Override
    public String toString() {
        return "CabRequest [area=" + area + ", location=" + location + "]";
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
}
