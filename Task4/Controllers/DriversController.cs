using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using VehiclesTrackingSystem.Models;

namespace VehiclesTrackingSystem.Controllers
{
    [Controller]
    [Route("[controller]")]
    public class DriversController : Controller
    {
        private readonly MyDbContext _context;

        public DriversController(MyDbContext context)
        {
            _context = context;
        }

        // GET: Drivers/Page
        [HttpGet("Page")]
        public IActionResult DriversPage()
        {
            var drivers = _context.Drivers.ToList();
            return View("Drivers", drivers);
        }

        // GET: Drivers
        [HttpGet]
        public IActionResult Index()
        {
            return RedirectToAction("DriversPage");
        }

        // GET: Drivers/AddDriver
        [HttpGet("AddDriver")]
        public IActionResult AddDriver()
        {
            return View("add-driver");
        }

        // POST: DriversApi
        [HttpPost]
        public async Task<IActionResult> PostDriver(Driver driver)
        {
            _context.Drivers.Add(driver);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(DriversPage));
        }

        // Ваши другие методы для отображения страниц здесь...
    }
}