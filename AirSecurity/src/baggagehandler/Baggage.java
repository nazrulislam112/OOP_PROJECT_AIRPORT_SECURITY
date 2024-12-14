package baggagehandler;

public class Baggage {
    private String baggageId;
    private String passengerName;
    private double weight;
    private String status;

    public Baggage(String baggageId, String passengerName, double weight, String status) {
        this.baggageId = baggageId;
        this.passengerName = passengerName;
        this.weight = weight;
        this.status = status;
    }

    public String getBaggageId() { return baggageId; }
    public String getPassengerName() { return passengerName; }
    public double getWeight() { return weight; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return baggageId + "," + passengerName + "," + weight + "," + status;
    }

    public static Baggage fromString(String data) {
        String[] fields = data.split(",");
        return new Baggage(fields[0], fields[1], Double.parseDouble(fields[2]), fields[3]);
    }
}
