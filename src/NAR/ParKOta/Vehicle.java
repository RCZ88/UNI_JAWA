package NAR.ParKOta;

public class Vehicle {
    private String licencePlate;
    private VType type;
    private int curPSId;

    public Vehicle(String licencePlate, VType type, int curPSId) {
        this.licencePlate = licencePlate;
        this.type = type;
        this.curPSId = curPSId;
    }

    public int getCurPSId() {
        return curPSId;
    }

    public void setCurPSId(int curPSId) {
        this.curPSId = curPSId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public VType getType() {
        return type;
    }

    public void setType(VType type) {
        this.type = type;
    }
}
