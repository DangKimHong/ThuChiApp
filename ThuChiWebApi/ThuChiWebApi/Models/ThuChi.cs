namespace ThuChiWebApi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("ThuChi")]
    public partial class ThuChi
    {
        public long ID { get; set; }

        [Required]
        [StringLength(250)]
        public string Ten { get; set; }

        public decimal SoTien { get; set; }

        [Column(TypeName = "date")]
        public DateTime Ngay { get; set; }
    }
}
