namespace VehiclesTrackingSystem.Models
{
    public class Driver
    {
        public int DriverId {  get; set; }
        public string FullName {  get; set; }
        public DateTime DateOfBirth { get; set;  }
        public string LicenseNumber { get; set; }
        public DateTime ExpirationDate {  get; set; }
        public string ContactNumber {get; set; }

    }
}
