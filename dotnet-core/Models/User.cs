using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace dotnet_core.Models
{
    [Table("users")]
    public partial class User
    {
        public int id { get; set; }
        public string first_name { get; set; }
        public string last_name { get; set; }
        public string email { get; set; }
        public ICollection<UserCountryMapping> userCountryMappings { get; set; }
    }
}
