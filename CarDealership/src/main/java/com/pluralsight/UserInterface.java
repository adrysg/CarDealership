package com.pluralsight;

import java.util.ArrayList;

public class UserInterface {

    public static final String filename = "inventory.csv";
    public Dealership currentDealership;


    public UserInterface(){
        currentDealership = DealershipFileManager.getFromCSV(filename);
    }


    public void display(){

        String options = """
                Please select from the following choices:
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit
                >>>\s""";

        int selection;

        // User Interface Loop
        do {
            System.out.println("Welcome to " + currentDealership.getName() + "!");
            selection = Console.PromptForInt(options);
            switch (selection) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> System.exit(0);
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } while (selection != 99);

    }

    private void processRemoveVehicleRequest() {
        System.out.println("What is the vehicle's Vin #: ");
        int vin = Console.PromptForInt("Vin #: ");

        for (Vehicle v : currentDealership.getInventory()) {

            if (v.getVin() == vin) {
                currentDealership.removeVehicleFromInventory(v);
            }
        }

        DealershipFileManager.saveToCSV(currentDealership,filename);
    }

    private void processAddVehicleRequest() {
        //get lots of values from the user...
        int vin = Console.PromptForInt("Enter Vin: ");
        int year = Console.PromptForInt("Enter year: ");
        String make = Console.PromptForString("Enter make: ");
        String model = Console.PromptForString("Enter model: ");
        String vehicleType = Console.PromptForString("Enter vehicle type: ");
        String color = Console.PromptForString("Enter color:  ");
        int odometer = Console.PromptForInt("Enter odometer: ");
        double price = Console.PromptForDouble("Enter price: ");

        Vehicle v = new Vehicle(vin,year, make, model, vehicleType, color, odometer, price);

        currentDealership.addVehicleToInventory(v);
        DealershipFileManager.saveToCSV(currentDealership, filename);

    }

    private void processGetByVehicleTypeRequest() {
        String type = Console.PromptForString("Enter Vehicle Type:  ").toLowerCase();
       displayVehicles(currentDealership.getVehiclesByType(type));

    }

    private void processGetByMileageRequest() {
        int min = Console.PromptForInt("Min Mileage:  ");
        int max = Console.PromptForInt("Max Mileage: ");

        displayVehicles(currentDealership.getVehiclesByMileage(min, max));


    }

    private void processGetByColorRequest() {
        String color = Console.PromptForString("Enter Color: ");

        displayVehicles(currentDealership.getVehiclesByColor(color));

    }

    private void processGetByYearRequest() {
        int min = Console.PromptForInt("Min Year:  ");
        int max = Console.PromptForInt("Max Year: ");

        displayVehicles(currentDealership.getVehiclesByYear(min, max));


    }

    private void processGetByMakeModelRequest() {
        String make = Console.PromptForString("Make of vehicle: ");
        String model = Console.PromptForString("Model of vehicle");

        displayVehicles(currentDealership.getVehiclesByMakeModel(make, model));

    }

    private void processGetByPriceRequest() {
        double min = Console.PromptForDouble("Enter min: ");
        double max = Console.PromptForDouble("Enter max: ");

        displayVehicles(currentDealership.getVehiclesByPrice(min, max));

    }

    public void processGetAllVehiclesRequest(){
        displayVehicles(currentDealership.getAllVehicles());
    }


    public void displayVehicles(ArrayList<Vehicle> vehicles){
        System.out.println(vehicles);
    }

}