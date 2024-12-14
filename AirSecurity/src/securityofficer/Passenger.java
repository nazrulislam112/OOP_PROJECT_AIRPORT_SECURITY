package securityofficer;
public class Passenger {
    private String boardingPassID;
    private String name;
    private String flightNumber;
    private String boardingTime;
    private String clearanceStatus;

    public Passenger(String boardingPassID, String name, String flightNumber, String boardingTime, String clearanceStatus) {
        this.boardingPassID = boardingPassID;
        this.name = name;
        this.flightNumber = flightNumber;
        this.boardingTime = boardingTime;
        this.clearanceStatus = clearanceStatus;
    }

    public String getBoardingPassID() {
        return boardingPassID;
    }

    public String getName() {
        return name;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public String getClearanceStatus() {
        return clearanceStatus;
    }

    public void setClearanceStatus(String clearanceStatus) {
        this.clearanceStatus = clearanceStatus;
    }

    @Override
    public String toString() {
        return boardingPassID + "," + name + "," + flightNumber + "," + boardingTime + "," + clearanceStatus;
    }
}
