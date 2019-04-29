package com.lb.richardk.lbfour;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddVehicleActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Car");
    DatabaseReference myRegRef = database.getReference("Registration");

    public EditText registration;
    //public EditText model;
    //public EditText colour;
    //public EditText make;
    public EditText city;
    private TextView error;

    public String uid;
    public String cit;
    public String vReg;

    public Car car;

    public long registrations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        registration = (EditText) findViewById(R.id.ViewCityeditText);
        //model = (EditText) findViewById(R.id.ViewModeleditText2);
        //colour = (EditText) findViewById(R.id.ViewColoureditText2);
        //make = (EditText) findViewById(R.id.ViewCityeditText);
        city = (EditText) findViewById(R.id.CityeditText);
        error = (TextView)findViewById(R.id.errorText);

        registration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable arg) {

                String s = arg.toString();
                if(!s.equals(s.toUpperCase())){
                    s = s.toUpperCase();
                    registration.setText(s);
                    registration.setSelection(registration.getText().length());

                }

            }
        });

        Button addBtn = (Button)findViewById(R.id.AddVehiclebutton);
        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                vReg = registration.getText().toString();
                //String mod = model.getText().toString();
                //String col = colour.getText().toString();
                //String mk = make.getText().toString();
                cit = city.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                uid = user.getUid();

                car = new Car(vReg, cit);

                DatabaseReference newCar = FirebaseDatabase.getInstance().getReference("Registration/"+vReg);
                newCar.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        registrations = dataSnapshot.getChildrenCount();
                        if(dataSnapshot.getChildrenCount() == 0)
                        {
                            myRef.child(uid).push().setValue(car);
                            myRegRef.child(vReg).child("uid").setValue(uid);

                            Intent startIntent = new Intent(getApplicationContext(), VehicleViewActivity.class);
                            startActivity(startIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Do something about the error
                    }
                });

                if(registrations > 0)
                {
                    error.setTextColor(Color.RED);
                    error.setText("This registration is already in use. Please contact support for help");
                }
            }
        });
    }
}
