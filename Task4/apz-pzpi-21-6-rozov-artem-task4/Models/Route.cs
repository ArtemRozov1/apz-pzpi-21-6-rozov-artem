namespace VehiclesTrackingSystem.Models
{
    public class Route
    {
        public int RouteId {  get; set; }
        public string StartPoint {  get; set; }
        public string EndPoint {  get; set; }
        public double Distance {  get; set; }
        public string EstimatedTime {  get; set; }

    }
}
