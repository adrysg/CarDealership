package com.pluralsight;

import java.io.*;

public class DealershipFileManager {


    public static Dealership getFromCSV(String filename){

        Dealership dealership = null;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String line;

            String[] firstLineData = bufferedReader.readLine().split("\\|");
            String name = firstLineData[0];
            String address = firstLineData[1];
            String phone = firstLineData[2];
            dealership = new Dealership(name, address, phone);

            while((line = bufferedReader.readLine()) != null){
                String[] newLine = line.split("\\|");
                if(newLine.length == 8){
                    int vinNumber = Integer.parseInt(newLine[0]);
                    int makeYear = Integer.parseInt(newLine[1]);
                    String make = newLine[2];
                    String model = newLine[3];
                    String vehicleType = newLine[4];
                    String color = newLine[5];
                    int odometer = Integer.parseInt(newLine[6]);
                    double price = Double.parseDouble(newLine[7]);
                    Vehicle v = new Vehicle(vinNumber, makeYear, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicleToInventory(v);
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return dealership;
    }

    public static void saveToCSV(String filename){

    }


}