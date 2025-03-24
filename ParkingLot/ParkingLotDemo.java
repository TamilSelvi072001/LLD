public class ParkingLotDemo
{
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();

        // Park Vehicles
        ParkingSpot carSpot = lot.parkVehicle("car");
        ParkingSpot bikeSpot = lot.parkVehicle("bike");

        // Free spots
        lot.leaveVehicle(0, carSpot);
        lot.leaveVehicle(0, bikeSpot);
    }
}