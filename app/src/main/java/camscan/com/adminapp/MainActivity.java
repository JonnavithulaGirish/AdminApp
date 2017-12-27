package camscan.com.adminapp;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
//import android.icu.text.SimpleDateFormat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText Data;
    Button setDate,addBus;
    ListView busList;
    Custom_Adapter customAdapter;
    ArrayList<Date> dates;
    String selected;
    ArrayList<Bus> bus;
    int count=0;
    SimpleDateFormat sdf;
    //Dates_Pojo dates_pojo;
    DatabaseReference myRef,myChildRef;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      bus=new ArrayList<>();
        setContentView(R.layout.activity_main);
        dates=new ArrayList<Date>();

        Data=(EditText)findViewById(R.id.busDate);
        setDate=(Button)findViewById(R.id.busDateOk);
        addBus=(Button)findViewById(R.id.addBus);
        busList=(ListView)findViewById(R.id.BusList);
        final Calendar myCalendar= Calendar.getInstance();

        database = FirebaseDatabase.getInstance();
        final DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "ddMMyy";
                 sdf = new SimpleDateFormat(myFormat, Locale.US);

                Data.setText(sdf.format(myCalendar.getTime()));
                selected=sdf.format(myCalendar.getTime());
                customAdapter=new Custom_Adapter(getApplicationContext(),bus);
              //  for(int i=0;i<dates.size();i++){
                //    if(sdf.format(myCalendar.getTime())==dates.get(i).Data);{
                  //   customAdapter=new Custom_Adapter(getApplicationContext(),dates.get(i).BusArrayList);
                    //busList.setAdapter(customAdapter);
                    //}
                //}
                setDate.setText("Edit Date");
                myChildRef=database.getReference(selected);
                listen();
            }
        };

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.performClick();
            }
        });
        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date ,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final Dialog dialog = new Dialog(MainActivity.this,R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Enter the data");
                final EditText id = (EditText) dialog.findViewById(R.id.busEditid);
                final EditText seats = (EditText) dialog.findViewById(R.id.busEditSeats);
                final EditText T_name=(EditText)dialog.findViewById(R.id.T_name);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);
                 Button cancel=(Button)dialog.findViewById(R.id.dialogCancel) ;
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bid=id.getText().toString();
                        int bseats=Integer.parseInt(seats.getText().toString());
                        String traveller_name=T_name.getText().toString().trim();
                        ArrayList<String> bseater=new ArrayList<String>();

                        for(int i=0;i<bseats;i++){
                            bseater.add("available");
                        }



                        Bus bus=new Bus(bid,bseats,bseats,bseater,traveller_name);
                        Bus_Pojo bus_pojo=new Bus_Pojo(bid,bseats,bseats,bseater,traveller_name);
                        int flag=0;
                        int content=0;
                        for(int i=0;i<dates.size();i++){
                         if(dates.get(i).Data.equalsIgnoreCase(selected)) {
                             flag = flag + 1;
                             content=i;
                         }
                        }
                        if(flag!=0){
                          dates.get(content).BusArrayList.add(bus);
                        }
                        else{
                            ArrayList<Bus> myBus=new ArrayList<Bus>();
                            myBus.add(bus);
                            Date k=new Date(selected,myBus);
                            dates.add(k);
                        }
                        myRef = database.getReference(selected).child(bid);

                        myRef.setValue(bus_pojo);
                        //count++;
                        dialog.dismiss();
                    }
                });
                dialog.show();




            }
        });


    }

    void listen()
    {

                myChildRef.addValueEventListener(new ValueEventListener() {
                    ArrayList<Bus> U_bus=new ArrayList<>();
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        U_bus.clear();
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            U_bus.add(dsp.getValue(Bus.class));
                        }
                        for(int i=0;i<U_bus.size();i++)
                        Log.d("MyTag", "onDataChange: "+U_bus.get(i).getId());
                      //  ArrayList <Bus> T_Bus=new ArrayList<>();
//                        if(count>0) {
//                           // customAdapter.notifyDataSetChanged();
//                            busList.setAdapter(customAdapter);
//                            Log.e("MYTag","Updated to null");
//                            customAdapter.setBus(U_bus);
//                            //customAdapter.notifyDataSetChanged();
//                            //busList.setAdapter(customAdapter);
//                            busList.setAdapter(new Custom_Adapter(getApplicationContext(),U_bus));
//                            Log.e("MYTag","Upadted to new Set");
//                            count=1;
//                            Log.e("MYTag","Count set to:"+count);
//                        }
//                        else if(count==0)
//                        {
//                            customAdapter.setBus(U_bus);
//
//                          //  customAdapter.notifyDataSetChanged();
//                            busList.setAdapter(new Custom_Adapter(getApplicationContext(),U_bus));
//                            count++;
//                            Log.e("MYTag","Count set to:"+count);
//                        }
                       // busList.setAdapter(customAdapter);
                            busList.setAdapter(new Custom_Adapter(getApplicationContext(),U_bus));

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



    }
}
