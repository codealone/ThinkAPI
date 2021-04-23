import bridgeModule.Item;

import java.util.List;

import bridgeAPI.Inventory;

public class MainTest {
	private static Item i = new Item();
	private static Inventory r = new Inventory();
	public static void main(String[] args) {
		i.setName("Laptop");
		i.setDescription("Computer");
		double d = 67500.80;
		float f = (float)d;
		i.setPrice(f);
		r.AddItem(i);
		i.setName("Earphone");
		i.setDescription("Listening device");
		d = 2000.75;
		f = (float)d;
		i.setPrice(f);
		r.AddItem(i);
		i.setId(2);
		r.EditItem(i);
		List<Item> ls = r.ListItem();
		for(Item it: ls) {
			System.out.print("ID ="+ it.getId() + " ");
			System.out.print("Name ="+ it.getName()+ " ");
			System.out.print("Description ="+ it.getDescription()+ " ");
			System.out.println("Price ="+ it.getPrice());
		}
		r.DeleteItem(2);
	}

}
