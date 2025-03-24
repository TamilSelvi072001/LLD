import java.util.*;

class ParkingLevel {
    private int levelId;
    private Queue<BikeSpot> bikeSpots;
    private Queue<CarSpot> carSpots;
    private Queue<TruckSpot> truckSpots;

    public ParkingLevel(int levelId, int numBikeSpots, int numCarSpots, int numTruckSpots) {
        this.levelId = levelId;
        this.bikeSpots = new LinkedList<>();
        this.carSpots = new LinkedList<>();
        this.truckSpots = new LinkedList<>();

        for (int i = 0; i < numBikeSpots; i++) {
            bikeSpots.add(new BikeSpot(i + 1));
        }
        for (int i = 0; i < numCarSpots; i++) {
            carSpots.add(new CarSpot(i + 1));
        }
        for (int i = 0; i < numTruckSpots; i++) {
            truckSpots.add(new TruckSpot(i + 1));
        }
    }

    public ParkingSpot parkVehicle(String type) {
        if (type.equals("bike") && !bikeSpots.isEmpty()) {
            return bikeSpots.poll();
        } else if (type.equals("car") && !carSpots.isEmpty()) {
            return carSpots.poll();
        } else if (type.equals("truck") && !truckSpots.isEmpty()) {
            return truckSpots.poll();
        }
        return null;
    }

    public void freeSpot(ParkingSpot spot) {
        spot.leave();
        if (spot instanceof BikeSpot) {
            bikeSpots.offer((BikeSpot) spot);
        } else if (spot instanceof CarSpot) {
            carSpots.offer((CarSpot) spot);
        } else if (spot instanceof TruckSpot) {
            truckSpots.offer((TruckSpot) spot);
        }
    }
}