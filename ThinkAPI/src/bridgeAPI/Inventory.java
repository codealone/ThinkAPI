package bridgeAPI;

import bridgeModule.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bridgeAPI.ConnectionDB;


public class Inventory {
	private static ConnectionDB con = new ConnectionDB();
	
	 public static boolean AddItem(Item e) {
	    	String query = "INSERT INTO Inventory"+"(Name,Description,Price)values(?,?,?)";
			Connection conn = null;
			PreparedStatement pstmt = null;
			if(!search(e.getId())) {
				try {
					conn = con.getconnection();
				} catch (Exception es) {
					es.printStackTrace();
				}
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, e.getName());
					pstmt.setString(2, e.getDescription());
					pstmt.setFloat(3, e.getPrice());
					pstmt.executeUpdate();
					System.out.println("Entry done");
					pstmt.close();
					return true;
				} catch (Exception es) {
					System.out.println("Catch");
					es.printStackTrace();
				}finally {
					con.closeconnection(conn);
				}
			}else {
	    		System.out.println("Id already exists");
	    	}
			return false;
	    }
	 
	 public static List<Item> ListItem() {
	    	List<Item> ls = new ArrayList<Item>();
			String query = "SELECT * FROM Inventory";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = con.getconnection();
			} catch (Exception es) {
				// TODO Auto-generated catch block
				es.printStackTrace();
			}
			try {
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				System.out.println("Listing");
				while(rs.next()) {
					Item e = new Item();
					e.setId(rs.getInt("Id"));
					e.setName(rs.getString("Name"));
					e.setDescription(rs.getString("Description"));
					e.setPrice(rs.getFloat("Price"));
					ls.add(e);
				}
				pstmt.close();
			} catch (Exception es) {
				System.out.println("List Not Available");
				es.printStackTrace();
			}finally {
				con.closeconnection(conn);
			}
			return ls;
	    }
	 
	 public static boolean DeleteItem(int id) {
	    	String query = "DELETE FROM Inventory WHERE Id="+id;
			Connection conn = null;
			PreparedStatement pstmt = null;
	    	if(search(id)) {
	    		try {
	    			conn = con.getconnection();
	    		} catch (Exception es) {
	    			// TODO Auto-generated catch block
	    			es.printStackTrace();
	    		}
	    		try {
	    			pstmt = conn.prepareStatement(query);
	    			pstmt.executeUpdate();
	    		    System.out.println("Deletion done");
	    		    pstmt.close();
	    		    return true;
	    		} catch (Exception es) {
	    			// TODO Auto-generated catch block
	    			System.out.println("Deletion Catch");
	    			es.printStackTrace();
	    		}finally {
	    			con.closeconnection(conn);
	    		}
	    	}else {
	    		System.out.println("Id does not exists");
	    	}
			return false;
	    }
	    
	    public static boolean EditItem(Item r) {
	    	String query = "UPDATE Inventory SET Name=?, Description=?, Price=? "
	    			+ "WHERE Id="+r.getId();
			Connection conn = null;
			PreparedStatement pstmt = null;
	    	if(search(r.getId())) {
	    		try {
	    			conn = con.getconnection();
	    		} catch (Exception es) {
	    			// TODO Auto-generated catch block
	    			es.printStackTrace();
	    		}
	    		try {
	    			pstmt = conn.prepareStatement(query);
	    			pstmt.setString(1, r.getName());
	    			pstmt.setString(2, r.getDescription());
	    			pstmt.setFloat(3, r.getPrice());
	    			pstmt.executeUpdate();
	    		    System.out.println("Edition done");
	    		    pstmt.close();
	    		    return true;
	    		} catch (Exception es) {
	    			// TODO Auto-generated catch block
	    			System.out.println("Edition Catch");
	    			es.printStackTrace();
	    		}finally {
	    			con.closeconnection(conn);
	    		}
	    	}else {
	    		System.out.println("Id does not exists");
	    	}
			return false;
	    }
	    
	    private static boolean search(int id) {
			String query = "SELECT * FROM Inventory WHERE Id="+id;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = con.getconnection();
			} catch (Exception es) {
				// TODO Auto-generated catch block
				es.printStackTrace();
			}
			try {
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				System.out.println("Searching");
				if(rs.next()) {
					System.out.println("Id found");
					pstmt.close();
					return true;
				}else {
					pstmt.close();
					return false;
				}
			} catch (Exception es) {
				// TODO Auto-generated catch block
				System.out.println("Catch search");
				es.printStackTrace();
			}finally {
				con.closeconnection(conn);
			}
			return false;
	    }
}
