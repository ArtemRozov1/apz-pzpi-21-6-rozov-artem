    using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using VehiclesTrackingSystem.Models;

namespace VehiclesTrackingSystem.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class UsersController : Controller
    {
        private readonly MyDbContext _context; 

        public UsersController(MyDbContext context)
        {
            _context = context;
        }

        /*[HttpPost("login")]
        public async Task<IActionResult> Login(LoginModel loginModel)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.Login == loginModel.Login && u.Password == loginModel.Password);
            if (user == null)
            {
                return NotFound("Пользователь не найден или неверные учетные данные.");
            }


            return Ok(user);
        }*/

        [HttpGet("login")]
        public IActionResult LoginView()
        {
            // Возвращаем представление для страницы входа (например, с именем "Login")
            return View("Index");
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginModel loginModel)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.Login == loginModel.Login && u.Password == loginModel.Password);
            if (user == null)
            {
                // Возвращаем объект JSON с сообщением об ошибке
                return BadRequest(new { message = "Пользователь не найден или неверные учетные данные." });
            }

            // Если пользователь найден и учетные данные правильные, выполните необходимые действия.
            // Например, войдите в аккаунт и перенаправьте на страницу api/drivers/page.

            // Возвращаем ответ с кодом 200 (OK) и объектом пользователя
            return Ok(user);
        }

        /*[HttpPost("register")]
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
        }*/
        [HttpGet("register")]
        public IActionResult RegisterView()
        {
            // Возвращаем представление для страницы регистрации (например, с именем "Registration")
            return View("Registration");
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register(RegistrationModel registerModel)
        {
            try
            {
                if (await _context.Users.AnyAsync(u => u.Login == registerModel.Login))
                {
                    return Conflict("User with this login already exists.");
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

                return Ok("Registration successful!");
            }
            catch (Exception ex)
            {
                return StatusCode(500, "An error occurred while processing your request.");
            }
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
