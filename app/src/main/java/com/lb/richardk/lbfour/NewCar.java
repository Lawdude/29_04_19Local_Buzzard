package com.lb.richardk.lbfour;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NewCar
{
    public String vehicleReg;
    //public String model;
    //public String make;
    //public String colour;
    public String city;

    public NewCar()
    {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public NewCar(String vReg, String city)
    {
        this.vehicleReg = vReg;
        //this.model = mod;
        //this.colour = col;
        //this.make = mk;
        this.city = city;
    }
}