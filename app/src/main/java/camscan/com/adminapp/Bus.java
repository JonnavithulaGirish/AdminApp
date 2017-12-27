package camscan.com.adminapp;

import java.util.ArrayList;

/**
 * Created by HULK on 11/23/2017.
 */

public class Bus {
    public String id;
    public int seats;
    public int available;
    public ArrayList<String> availability;
    public String travels;
    public Bus()
    {}

    public Bus(String id,int seats, int available,ArrayList<String> availability,String travels)
    {
        this.id=id;
        this.seats=seats;
        this.available=available;
        this.availability=availability;
        this.travels=travels;
    }
    public void setAvailability(ArrayList<String> availability)
    {
        this.availability=availability;
    }
    public ArrayList<String> getAvailability()
    {
        return availability;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public void setSeats(int seats)
    {
        this.seats=seats;
    }
    public  void setAvailable(int available)
    {
        this.available=available;
    }
    public int getSeats()
    {
        return seats;
    }
    public String getId()
    {
        return id;
    }
    public  int getAvailable()
    {
        return available;
    }
    public void setTravels(String travels)
    {
        this.travels=travels;
    }
    public String getTravels()
    {
        return  travels;
    }

}
