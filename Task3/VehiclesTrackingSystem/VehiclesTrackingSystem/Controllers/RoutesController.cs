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
    public class RoutesController : Controller
    {
        private readonly MyDbContext _context;

        public RoutesController(MyDbContext context)
        {
            _context = context;
        }

        // GET: Routes/Page
        [HttpGet("Page")]
        public IActionResult RoutesPage()
        {
            var routes = _context.Routes.ToList();
            return View("Routes", routes);
        }

        // GET: Routes
        [HttpGet]
        public IActionResult Index()
        {
            return RedirectToAction("RoutesPage");
        }

        // GET: Routes/AddRoute
        [HttpGet("AddRoute")]
        public IActionResult AddRoute()
        {
            return View("add-route");
        }

        // POST: Routes
        [HttpPost]
        public async Task<IActionResult> PostRoute(Models.Route route)
        {
            _context.Routes.Add(route);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(RoutesPage));
        }

        // GET: Routes/EditRoute/5
        [HttpGet("EditRoute/{id}")]
        public async Task<IActionResult> EditRoute(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var route = await _context.Routes.FindAsync(id);
            if (route == null)
            {
                return NotFound();
            }

            return View("edit-route", route);
        }

        // POST: Routes/EditRoute/5
        [HttpPost("EditRoute/{id}")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> EditRoute(int id, [Bind("RouteId,StartPoint,EndPoint,Distance,EstimatedTime")] Models.Route route)
        {
            if (id != route.RouteId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(route);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!RouteExists(route.RouteId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(RoutesPage));
            }
            return View("edit-route", route);
        }

        // GET: Routes/DeleteRoute/5
        [HttpGet("DeleteRoute/{id}")]
        public async Task<IActionResult> DeleteRoute(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var route = await _context.Routes.FirstOrDefaultAsync(m => m.RouteId == id);
            if (route == null)
            {
                return NotFound();
            }

            return View("delete-route", route);
        }

        // DELETE: Routes/DeleteRoute/{id}
        [HttpDelete("DeleteRoute/{id}")]
        public async Task<IActionResult> DeleteRoute(int id)
        {
            var route = await _context.Routes.FindAsync(id);
            if (route == null)
            {
                return NotFound();
            }

            _context.Routes.Remove(route);
            await _context.SaveChangesAsync();
            return NoContent();
        }


        private bool RouteExists(int id)
        {
            return _context.Routes.Any(e => e.RouteId == id);
        }
    }
}
