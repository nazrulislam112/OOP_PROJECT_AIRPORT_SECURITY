package securityofficer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "file/passengerData.txt";

    // Read all passengers from the file
    public static List<Passenger> readPassengers() throws IOException {
    List<Passenger> passengers = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
    String line;
    while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) {
            continue; // Skip empty lines
        }
        String[] data = line.split(",");
        if (data.length < 5) {
            System.err.println("Invalid line: " + line); // Log the issue
            continue; // Skip this line
        }
        Passenger passenger = new Passenger(data[0], data[1], data[2], data[3], data[4]);
        passengers.add(passenger);
    }
    reader.close();
    return passengers;
    }

    // Write all passengers back to the file
    public static void writePassengers(List<Passenger> passengers) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Passenger passenger : passengers) {
            writer.write(passenger.toString());
            writer.newLine();
        }
        writer.close();
    }

    // Update clearance status
    public static void updateClearanceStatus(String boardingPassID, String status) throws IOException {
        List<Passenger> passengers = readPassengers();
        boolean updated = false;
        for (Passenger passenger : passengers) {
            if (passenger.getBoardingPassID().equals(boardingPassID)) {
                passenger.setClearanceStatus(status);
                updated = true;
            }
        }
        writePassengers(passengers);
        if (!updated) {
            throw new IllegalArgumentException("Passenger not found with ID: " + boardingPassID);
        }
    }
}
