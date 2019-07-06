package com.example.diannaliset.vehicles.handlerDB;

/*
    this clas provide all needed vehicle attributes.
    When we need make a CRUD then will be essential use a Vehicle object.
 */

public class Vehicle {

    /*
        All object attributes
     */
    private String plaque;
    private String brand;
    private String model;
    private int numOfDoors;
    private String typeOfVehicle;
    private int tireColor;
    private int capoColor;
    private int doorColor;


    public Vehicle(String plaque, String brand, String model, int numOfDoors, String typeOfVehicle, int tireColor, int capoColor,int doorColor){
        this.plaque=plaque;
        this.brand=brand;
        this.model=model;
        this.numOfDoors=numOfDoors;
        this.typeOfVehicle=typeOfVehicle;
        this.tireColor=tireColor;
        this.capoColor=capoColor;
        this.doorColor=doorColor;
    }
    public Vehicle(){

    }

    /*
       (get) and (set) methods
     */
    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public int getTireColor() {
        return tireColor;
    }

    public void setTireColor(int tireColor) {
        this.tireColor = tireColor;
    }

    public int getCapoColor() {
        return capoColor;
    }

    public void setCapoColor(int capoColor) {
        this.capoColor = capoColor;
    }

    public int getDoorColor() {
        return doorColor;
    }

    public void setDoorColor(int doorColor) {
        this.doorColor = doorColor;
    }
}
