import java.util.HashMap;
import java.util.Map;

class ParkingLot {
    private static ParkingLot instance;
    private Map<Integer, ParkingLevel> levels;

    private final int TOTAL_LEVELS = 5;

    private ParkingLot() {
        levels = new HashMap<>();
        for (int i = 0; i < TOTAL_LEVELS; i++) {
            levels.put(i, new ParkingLevel(i, 2, 3, 5)); // Example spot distribution
        }
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public ParkingSpot parkVehicle(String type) {
        for (ParkingLevel level : levels.values()) {
            ParkingSpot spot = level.parkVehicle(type);
            if (spot != null) {
                System.out.println("Parked at Level: " + level);
                return spot;
            }
        }
        System.out.println("No available spots for " + type);
        return null;
    }

    public void leaveVehicle(int levelId, ParkingSpot spot) {
        if (levels.containsKey(levelId)) {
            levels.get(levelId).freeSpot(spot);
        }
    }
}