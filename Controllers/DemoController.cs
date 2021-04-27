using BridgeThAPI.Models;
using BridgeThAPI.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace BridgeThAPI.Controllers
{
    
    public class DemoController : ApiController
    {
        private ItemService IItemService = new ItemService();
        private ItemModel item = new ItemModel();

        [HttpPut]
        public async Task<IHttpActionResult> ListAllAsync()
        {
            List<ItemModel> items = new List<ItemModel>();
            items =  IItemService.GetItems();
            await Task.Delay(500);
            if (items == null || items.Count == 0)
                return NotFound();
            return Ok(items);
        }

        [HttpPost]
        public async Task<IHttpActionResult> ModifyItem(ItemModel item)
        {
            bool isupdated = IItemService.UpdateDetails(item);
            await Task.Delay(500);
            if (isupdated)
                return NotFound();
            return Ok();
        }

        [HttpPost]
        public async Task<IHttpActionResult> AddItem(ItemModel item)
        {
            bool isadded = IItemService.AddItem(item);
            await Task.Delay(500);
            if (isadded)
                return NotFound();
            return Ok();
        }

        [HttpGet]
        public async Task<IHttpActionResult> DeleteItem(int id)
        {
            bool isdeleted = IItemService.DeleteItem(id);
            await Task.Delay(500);
            if (isdeleted)
                return NotFound();
            return Ok();
        }

    }
}
