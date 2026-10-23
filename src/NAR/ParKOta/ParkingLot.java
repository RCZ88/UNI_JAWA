package NAR.ParKOta;

import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<ParkingSlot> slots;
    public ParkingLot(){
        slots = new ArrayList<>();
    }

    public ArrayList<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<ParkingSlot> slots) {
        this.slots = slots;
    }

    public int getEmptyParkingSlot(){
        for (int i = 0; i < slots.size(); i++) {
            if(!slots.get(i).occupied){
                return i;
            }
        }
        return -1;
    }

    public void freeSlot(int id){
        slots.remove(id);
    }
}
