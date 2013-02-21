import java.util.ArrayList;
/**
 * 
 * @author Huaxia Wang
 *
 */

public class routerTable {
	//store the routing table in a arraylist
	ArrayList<Integer> Table = new ArrayList<Integer>();
	/**
	 * @param table
	 */
	
	routerTable(ArrayList<Integer> table) {
		super();
		Table = table;
	}
	
	//update the value of routing table in the arraylist
	public void update(routerTable t, ArrayList<Integer> a){
		t.Table = a;
	}
	
}
