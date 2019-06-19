using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Newtonsoft.Json;

namespace dotnet_core.Models
{
    [Table("countries")]
    public partial class Country
    {
        public int id { get; set; }
        public string name { get; set; }
        [JsonIgnore]
        public ICollection<UserCountryMapping> userCountryMappings { get; set; }
    }
}
