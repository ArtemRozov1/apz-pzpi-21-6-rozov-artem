using Microsoft.EntityFrameworkCore;
using VehiclesTrackingSystem.Models;

namespace VehiclesTrackingSystem
{
    public class MyDbContext : DbContext
    {
        public MyDbContext(DbContextOptions<MyDbContext> options) : base(options)
        {
        }

        public DbSet<Vehicle> Vehicles { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<Driver> Drivers { get; set; }
        public DbSet<Models.Route> Routes { get; set; }
        public DbSet<Transportation> Transportations { get; set; }
    }
}
