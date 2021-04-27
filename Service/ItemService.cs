using BridgeThAPI.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace BridgeThAPI.Service
{
    public class ItemService
    {
        private SqlConnection con;
        private void connection()
        {
            string constring = ConfigurationManager.ConnectionStrings["Itemconn"].ToString();
            con = new SqlConnection(constring);
        }

        // **************** ADD NEW ITEM *********************
        public bool AddItem(ItemModel imodel)
        {
            connection();
            SqlCommand cmd = new SqlCommand("AddNewItem", con);
            cmd.CommandType = CommandType.StoredProcedure;

            cmd.Parameters.AddWithValue("Name", imodel.Name);
            cmd.Parameters.AddWithValue("Description", imodel.Description);
            cmd.Parameters.AddWithValue("Price", imodel.Price);
            int i = 0;
            try
            {
                con.Open();
                i = cmd.ExecuteNonQuery();
            }catch(Exception e)
            {

            }
            finally
            {
                con.Close();
            }
            if (i >= 1)
                return true;
            else
                return false;
        }

        // ********** VIEW ALL ITEM DETAILS ********************
        public List<ItemModel> GetItems()
        {
            connection();
            List<ItemModel> Itemlist = new List<ItemModel>();

            SqlCommand cmd = new SqlCommand("ListAll", con);
            cmd.CommandType = CommandType.StoredProcedure;
            SqlDataAdapter sd = new SqlDataAdapter(cmd);
            DataTable dt = new DataTable();

            con.Open();
            sd.Fill(dt);
            con.Close();

            foreach (DataRow dr in dt.Rows)
            {
                Itemlist.Add(
                    new ItemModel
                    {
                        Id = (dr["Id"] == null ? 0 : Convert.ToInt32(dr["Id"])),
                        Name = Convert.ToString(dr["Name"]),
                        Description = Convert.ToString(dr["Description"]),
                        Price = (dr["Price"] == null ? 0 : (float)Convert.ToDecimal(dr["Price"]))
                    });
            }
            return Itemlist;
        }

        // ***************** UPDATE Item DETAILS *********************
        public bool UpdateDetails(ItemModel smodel)
        {
            connection();
            SqlCommand cmd = new SqlCommand("UpdateItem", con);
            cmd.CommandType = CommandType.StoredProcedure;

            cmd.Parameters.AddWithValue("Id", smodel.Id);
            cmd.Parameters.AddWithValue("Name", smodel.Name);
            cmd.Parameters.AddWithValue("Description", smodel.Description);
            cmd.Parameters.AddWithValue("Price", smodel.Price);
            int i = 0;
            try
            {
                con.Open();
                i = cmd.ExecuteNonQuery();
            }
            catch (Exception e)
            {

            }
            finally
            {
                con.Close();
            }

            if (i >= 1)
                return true;
            else
                return false;
        }

        // ********************** DELETE Item DETAILS *******************
        public bool DeleteItem(int id)
        {
            connection();
            SqlCommand cmd = new SqlCommand("DeleteItem", con);
            cmd.CommandType = CommandType.StoredProcedure;

            cmd.Parameters.AddWithValue("Id", id);

            int i = 0;
            try
            {
                con.Open();
                i = cmd.ExecuteNonQuery();
            }
            catch (Exception e)
            {

            }
            finally
            {
                con.Close();
            }

            if (i >= 1)
                return true;
            else
                return false;
        }
    }
}