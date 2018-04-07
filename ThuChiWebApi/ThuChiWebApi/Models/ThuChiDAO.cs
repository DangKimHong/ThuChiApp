using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ThuChiWebApi.Models
{
    public class ThuChiDAO
    {
        private QuanLyDbContext db;
        public ThuChiDAO()
        {
            db = new QuanLyDbContext();
        }

        public IEnumerable<ThuChi> ListAll()
        {
            return db.ThuChi.OrderByDescending(s => s.ID);
        }

        public ThuChi GetByID(long id)
        {
            return db.ThuChi.Find(id);
        }

        public long Insert(ThuChi obj)
        {
            db.ThuChi.Add(obj);
            db.SaveChanges();
            return obj.ID;
        }

        public bool Update(ThuChi obj)
        {
            try
            {
                var thuChi = db.ThuChi.Find(obj.ID);
                thuChi.Ten = obj.Ten;
                thuChi.SoTien = obj.SoTien;
                thuChi.Ngay = obj.Ngay;

                db.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }

        public bool Delete(long id)
        {
            try
            {
                var thuChi = db.ThuChi.Find(id);
                db.ThuChi.Remove(thuChi);
                db.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }
    }
}