namespace VehiclesTrackingSystem.Models
{
    public class Transportation
    {
        public int TransportationId { get; set; }
        public int VehicleId { get; set; }
        public int DriverId { get; set; }
        public int RouteId { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
        public string CargoDescription { get; set; }
        public double CargoWeight { get; set; }
    }

}
