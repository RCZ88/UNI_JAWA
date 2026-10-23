package NAR.ParKOta;

public class ParkingSlot {
    Vehicle currentOccupiedBy;
    boolean occupied;

    public void occupySlot(Vehicle vehicle){
        occupied = true;
        currentOccupiedBy = vehicle;
    }
}
