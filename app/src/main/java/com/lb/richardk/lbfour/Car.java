package com.lb.richardk.lbfour;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Car
{
    public String vehicleReg;
    //public String model;
    //public String colour;
    //public String make;
    public String city;

    public Car()
    {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Car(String vReg, String cit)
    {
        this.vehicleReg = vReg;
        //this.model = mod;
        //this.colour = col;
        //this.make = mk;
        this.city = cit;
    }
}
