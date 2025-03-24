abstract class ParkingSpot {
    int id;
    boolean isOccupied;

    public ParkingSpot(int id) {
        this.id = id;
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public void park() {
        this.isOccupied = true;
    }

    public void leave() {
        this.isOccupied = false;
    }
}