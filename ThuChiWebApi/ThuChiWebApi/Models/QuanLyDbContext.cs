namespace ThuChiWebApi.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class QuanLyDbContext : DbContext
    {
        public QuanLyDbContext()
            : base("name=QuanLyDbContext")
        {
        }

        public virtual DbSet<ThuChi> ThuChi { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
