using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace dotnet_core.Models
{
    [Table("user_country_mapping")]
    public partial class UserCountryMapping
    {
        public int id { get; set; }
        public int user_id { get; set; }
        public int country_id { get; set; }
        public User user { get; set; }
        public Country country { get; set; }
    }
}
