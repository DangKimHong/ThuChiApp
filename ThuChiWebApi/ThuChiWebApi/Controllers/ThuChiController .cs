using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ThuChiWebApi.Models;

namespace ThuChiWebApi.Controllers
{
    public class ThuChiController : ApiController
    {
        // GET api/values
        /// <summary>
        /// Lay toan bo du lieu thu chi hien co
        /// </summary>
        /// <returns></returns>
        public IEnumerable<ThuChi> Get()
        {
            return new ThuChiDAO().ListAll();
        }

        // GET api/values/5
        /// <summary>
        /// Lay thong tin theo id
        /// </summary>
        /// <param name="id">id can xem</param>
        /// <returns></returns>
        public ThuChi Get(long id)
        {
            return new ThuChiDAO().GetByID(id);
        }

        // POST api/values
        /// <summary>
        /// Them moi du lieu
        /// </summary>
        /// <param name="obj">Du lieu can them moi</param>
        /// <returns></returns>
        public long Post([FromBody]ThuChi obj)
        {
            long res = new ThuChiDAO().Insert(obj);
            return res;
        }

        // PUT api/values/5
        public string Put(int id, [FromBody]ThuChi obj)
        {
            obj.ID = id;
            bool res = new ThuChiDAO().Update(obj);
            if (res)
            {
                return "success";
            }
            return "fail";
        }

        // DELETE api/values/5
        public string Delete(long id)
        {
            bool res = new ThuChiDAO().Delete(id);
            if (res)
            {
                return "success";
            }
            return "fail";
        }
    }
}
