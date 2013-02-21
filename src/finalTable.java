import java.util.ArrayList;

/**
 * 
 * @author Huaxia Wang
 *
 */

public class finalTable {
	
	private static final int infinity = 10000;
	
	ArrayList<Integer> RoutingTable = new ArrayList<Integer>();
	
	public void RoutingTable(ArrayList<Integer> a, int size){
		//Define -1 to be infinity
		for(int i = 0; i < size*size; i++)
			if(a.get(i) < 0)
				a.set(i, infinity);
		
		//initialize routing table
		RoutingTable.clear();
		
		for(int i = 0; i < size*size; i++)
			RoutingTable.add(a.get(i));
		
		//use BF algorithm to compute the routing table
		for(int n = 1; n < size; n++)
			for(int v = 0; v < size; v++)
				for(int i = 0; i < size; i++)
					for(int j = 0; j < size; j++){
						if(RoutingTable.get(v*size + i) > (a.get(v*size + j)<0?infinity:a.get(v*size + j)) + RoutingTable.get(j*size + i)){
							RoutingTable.set(v*size+i, a.get(v*size + j) + RoutingTable.get(j*size + i));
						}
					}
		
		//Redefine infinity to -1
		for(int i = 0; i < size*size; i++)
			if(RoutingTable.get(i) >= infinity)
				RoutingTable.set(i, -1);
	}

}
