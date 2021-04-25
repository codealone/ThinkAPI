package Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.databind.util.JSONPObject;

import Service.InventoryService;
import dao.Item;

@Path("/Inventory")
public class ThinkAPI {
	  private InventoryService iservice = new InventoryService();
	  // This method is called to add an Item
	  @POST
	  @Path("/Add/{Item}")
	  @Produces("application/json")
	  public JSONPObject AddItem(Item i) {
		boolean bt = iservice.insertitem(i);
		return new JSONPObject("IsAdded", bt);
	  }
	  
	  // This method is called to edit an Item
	  @POST
	  @Path("/Edit/{Item}")
	  @Produces("application/json")
	  public JSONPObject EditItem(Item i) {
		  boolean bt = iservice.updateitem(i);
		  return new JSONPObject("IsEdited", bt);
	  }
	  
	  // This method is called to delete an Item
	  @POST
	  @Path("/Delete/{id}")
	  @Produces("application/json")
	  public JSONPObject Delete(int id) {
		  boolean bt = iservice.removeitem(id);
		  return new JSONPObject("IsEdited",bt);
	  }
	  
	  // This method is called to list all Item
	  @PUT
	  @Path("/List")
	  @Produces("application/json")
	  public JSONPObject ListAll() {
	    List<Item> allItem = new ArrayList<Item>();
	    allItem = iservice.list();
		return new JSONPObject("Items",allItem);    
	  }
}
