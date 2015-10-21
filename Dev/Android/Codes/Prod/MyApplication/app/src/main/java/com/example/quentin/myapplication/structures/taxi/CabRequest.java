package com.example.quentin.myapplication.structures.taxi;

public class CabRequest {

    private String area;
    private LocVertex location;

    public CabRequest() {
        area="";
        location=new LocVertex();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocVertex getLocation() {
        return location;
    }

    public void setLocation(LocVertex location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CabRequest [area=" + area + ", location=" + location + "]";
    }

}
