using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VehiclesTrackingSystem.Models;

namespace VehiclesTrackingSystem.Controllers
{
    [Controller]
    [Route("[controller]")]
    public class VehiclesController : Controller
    {
        private readonly MyDbContext _context;

        public VehiclesController(MyDbContext context)
        {
            _context = context;
        }

        // GET: Vehicles/Page
        [HttpGet("Page")]
        public IActionResult VehiclesPage()
        {
            var vehicles = _context.Vehicles.ToList();
            return View("Vehicles", vehicles);
        }

        // GET: Vehicles
        [HttpGet]
        public IActionResult Index()
        {
            return RedirectToAction("VehiclesPage");
        }

        // GET: Vehicles/AddVehicle
        [HttpGet("AddVehicle")]
        public IActionResult AddVehicle()
        {
            return View("add-vehicle");
        }

        // POST: Vehicles
        [HttpPost]
        public async Task<IActionResult> PostVehicle([FromBody] Vehicle vehicle)
        {
            if (ModelState.IsValid)
            {
                _context.Vehicles.Add(vehicle);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(VehiclesPage));
            }
            return View("AddVehicle", vehicle);
        }

        // GET: Vehicles/EditVehicle/5
        [HttpGet("EditVehicle/{id}")]
        public async Task<IActionResult> EditVehicle(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vehicle = await _context.Vehicles.FindAsync(id);
            if (vehicle == null)
            {
                return NotFound();
            }

            return View("edit-vehicle", vehicle);
        }

        // POST: Vehicles/EditVehicle/5
        [HttpPost("EditVehicle/{id}")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> EditVehicle(int id, [Bind("VehicleId,VehicleNumber,VehicleType,Manufacturer,Model,YearOfManufacture,OwnerId,FactoryWeight,CurrentWeight,Location,TankCapacity,CurrentFuelLevel")] Vehicle vehicle)
        {
            if (id != vehicle.VehicleId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(vehicle);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!VehicleExists(vehicle.VehicleId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(VehiclesPage));
            }
            return View("edit-vehicle", vehicle);
        }

        // GET: Vehicles/DeleteVehicle/5
        [HttpGet("DeleteVehicle/{id}")]
        public async Task<IActionResult> DeleteVehicle(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var vehicle = await _context.Vehicles.FirstOrDefaultAsync(m => m.VehicleId == id);
            if (vehicle == null)
            {
                return NotFound();
            }

            return View("delete-vehicle", vehicle);
        }

        // DELETE: Vehicles/DeleteVehicle/{id}
        [HttpDelete("DeleteVehicle/{id}")]
        public async Task<IActionResult> DeleteVehicle(int id)
        {
            var vehicle = await _context.Vehicles.FindAsync(id);
            if (vehicle == null)
            {
                return NotFound();
            }

            _context.Vehicles.Remove(vehicle);
            await _context.SaveChangesAsync();
            return NoContent();
        }

        private bool VehicleExists(int id)
        {
            return _context.Vehicles.Any(e => e.VehicleId == id);
        }

        [HttpPut("UpdateLocation/{id}")]
        public async Task<IActionResult> UpdateLocation(int id, [FromBody] LocationUpdate locationUpdate)
        {
            var vehicle = await _context.Vehicles.FindAsync(id);
            if (vehicle == null)
            {
                return NotFound();
            }

            vehicle.Location = locationUpdate.NewLocation;

            try
            {
                _context.Update(vehicle);
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!VehicleExists(vehicle.VehicleId))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // GET: Vehicles/GetVehicleById/{id}
        [HttpGet("GetVehicleById/{id}")]
        public async Task<IActionResult> GetVehicleById(int id)
        {
            var vehicle = await _context.Vehicles.FindAsync(id);
            if (vehicle == null)
            {
                return NotFound();
            }

            return Ok(vehicle);
        }
    }

    public class LocationUpdate
    {
        public string NewLocation { get; set; }
    }
}
