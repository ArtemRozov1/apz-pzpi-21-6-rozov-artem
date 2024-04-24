    using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using VehiclesTrackingSystem.Models;

namespace VehiclesTrackingSystem.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class UsersController : ControllerBase
    {
        private readonly MyDbContext _context; 

        public UsersController(MyDbContext context)
        {
            _context = context;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginModel loginModel)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.Login == loginModel.Login && u.Password == loginModel.Password);
            if (user == null)
            {
                return NotFound("Пользователь не найден или неверные учетные данные.");
            }


            return Ok(user);
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register(RegistrationModel registerModel)
        {
            if (await _context.Users.AnyAsync(u => u.Login == registerModel.Login))
            {
                return Conflict("Пользователь с таким логином уже существует.");
            }

            var newUser = new User
            {
                Login = registerModel.Login,
                Password = registerModel.Password,
                Email = registerModel.Email,
                UserType = registerModel.UserType
            };

            _context.Users.Add(newUser);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetUserById), new { id = newUser.UserId }, newUser);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetUserById(int id)
        {
            var user = await _context.Users.FindAsync(id);
            if (user == null)
            {
                return NotFound("Пользователь не найден.");
            }

            return Ok(user);
        }
    }
}
