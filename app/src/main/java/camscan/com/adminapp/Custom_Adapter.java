package camscan.com.adminapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HULK on 11/22/2017.
 */

public class Custom_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Bus> bus=new ArrayList<>();
    LayoutInflater inflater;

    public Custom_Adapter(Context context, ArrayList<Bus> bus ){
        this.context=context;
        this.bus.clear();
        this.bus=bus;

    }
   public void clearList()
    {
        if(bus!=null)
        bus.clear();
  }
    public void setBus(ArrayList <Bus> bus)
    {
        this.bus=bus;
    }
    @Override
    public int getCount() {
        Log.d("mytag", "setBus: "+bus.size());
        return bus.size();
    }

    @Override
    public Object getItem(int position) {
        return bus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.from(context).inflate(R.layout.list_customview,null);
        TextView Traveller_n=(TextView) convertView.findViewById(R.id.traveller_n);
        TextView available=(TextView) convertView.findViewById(R.id.available);
        TextView busPlateNo=(TextView)convertView.findViewById(R.id.plate_no);
        Traveller_n.setText(bus.get(position).id+"");
        available.setText("Available: "+bus.get(position).available+"/"+bus.get(position).seats);
        busPlateNo.setText("Plate number: "+bus.get(position).id);
        return convertView;
    }
}

