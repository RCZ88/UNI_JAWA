package NAR.ParKOta;

import java.time.LocalDateTime;

public class TransactionLog {
    private LocalDateTime entry;
    private LocalDateTime exit;
    private Vehicle vehicle;
    private int parkingLotId;
    private int duration;
    private int cost;

    public TransactionLog(LocalDateTime entry, Vehicle vehicle, int parkingLotId) {
        this.entry = entry;
        this.parkingLotId = parkingLotId;
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
                "entry=" + entry +
                ", exit=" + exit +
                ", vehicle=" + vehicle +
                ", parkingLotId=" + parkingLotId +
                ", duration=" + duration +
                ", cost=" + cost +
                '}';
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public void setEntry(LocalDateTime entry) {
        this.entry = entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
