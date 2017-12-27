package camscan.com.adminapp;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

/**
 * Created by HULK on 11/22/2017.
 */

public class Date {
    public String Data;
    public ArrayList<Bus> BusArrayList;


    Date(String getDate,ArrayList<Bus> BusArrayList){
        this.Data=getDate;
        this.BusArrayList=BusArrayList;
    }

public void setData(String Data){
    this.Data=Data;
}
public void setBusArrayList(ArrayList<Bus> BusArrayList){
    this.BusArrayList=BusArrayList;

}

public ArrayList<Bus> getBusArrayList()
{
    return BusArrayList;
}
public String getData()
{
    return Data;
}

}
