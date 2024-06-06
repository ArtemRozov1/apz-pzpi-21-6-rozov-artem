using System.Reflection;

namespace VehiclesTrackingSystem.Models
{
    public class Vehicle
    {
        public int VehicleId { get; set; }
        public string VehicleNumber { get; set; }
        public string VehicleType { get; set; }
        public string Manufacturer { get; set; }
        public string Model { get; set; }
        public int YearOfManufacture { get; set; }
        public int OwnerId { get; set; }
        public double FactoryWeight { get; set; } 
        public double CurrentWeight { get; set; }
        public string Location { get; set; }
        public double TankCapacity { get; set; } 
        public double CurrentFuelLevel { get; set; } 
    }


}
