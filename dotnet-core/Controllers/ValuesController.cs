using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using dotnet_core.Models;
using dotnet_core.Data;

namespace dotnet_core.Controllers
{
    [ApiController]
    public class ValuesController : ControllerBase
    {
        private readonly ApplicationDbContext context;

        public ValuesController(ApplicationDbContext context)
        {
            this.context = context;
        }

        [Route("hello")]
        [HttpGet]
        public object hello()
        {
            return Ok(new { hello = "world" });
        }

        [Route("compute")]
        [HttpGet]
        public object compute()
        {
            int x = 0, y = 1, z, max;

            Random r = new Random();
            max = 10000 + r.Next(500);

            for (int i = 0; i <= max; i++) {
                z = x + y;
                x = y;
                y = z;
            }

            return Ok(new { status = "done" });
        }

        [Route("countries")]
        [HttpGet]
        public IEnumerable<Country> countries()
        {
            return context.Country.ToList();
        }

        [Route("users")]
        [HttpGet]
        public object users()
        {
            return context.UserCountryMapping
                .Where(uc => uc.country.name.Equals("France"))
                .Select(uc => new {
                    id = uc.user.id,
                    email = uc.user.email,
                    first_name = uc.user.first_name,
                    last_name = uc.user.last_name,
                    countries = uc.user.userCountryMappings.Select(m => m.country)
                })
                .ToList();
        }
    }
}
