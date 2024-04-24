using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using VehiclesTrackingSystem.Models;

namespace VehiclesTrackingSystem.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TransportationsController : ControllerBase
    {
        private readonly MyDbContext _context;

        public TransportationsController(MyDbContext context)
        {
            _context = context;
        }

        // GET: api/Transportations
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Transportation>>> GetTransportations()
        {
            return await _context.Transportations.ToListAsync();
        }

        // GET: api/Transportations/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Transportation>> GetTransportation(int id)
        {
            var transportation = await _context.Transportations.FindAsync(id);

            if (transportation == null)
            {
                return NotFound();
            }

            return transportation;
        }

        // POST: api/Transportations
        [HttpPost]
        public async Task<ActionResult<Transportation>> PostTransportation(Transportation transportation)
        {
            _context.Transportations.Add(transportation);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetTransportation), new { id = transportation.TransportationId }, transportation);
        }

        // PUT: api/Transportations/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTransportation(int id, Transportation transportation)
        {
            if (id != transportation.TransportationId)
            {
                return BadRequest();
            }

            _context.Entry(transportation).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TransportationExists(id))
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

        // DELETE: api/Transportations/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTransportation(int id)
        {
            var transportation = await _context.Transportations.FindAsync(id);
            if (transportation == null)
            {
                return NotFound();
            }

            _context.Transportations.Remove(transportation);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool TransportationExists(int id)
        {
            return _context.Transportations.Any(e => e.TransportationId == id);
        }
    }
}
