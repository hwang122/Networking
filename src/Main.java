import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Huaxia Wang
 *
 */
public class Main {	
	/**
	 * @param args
	 */

	private static Scanner scan;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		routerTable Router = new routerTable(null);
		loadfile lf = new loadfile();
		finalTableForSelectedRouter fs = new finalTableForSelectedRouter();
		finalTable ft = new finalTable();
		linkFailure lfail = new linkFailure();
		shortestPath sp = new shortestPath();
		
		while(true)
		{
			//menu
			System.out.println("1-LOAD FILE");
			System.out.println("2-OUTPUT FINAL TABLE FOR A SELECTED ROUTER");
			System.out.println("3-COMPUTE AND OUTPUT FINAL ROUTING TABLE");
			System.out.println("4-LINK FAILURE");
			System.out.println("5-OUTPUT OPTIMAL PATH AND MINIMUM COST");
			
			InputStream is = System.in;
			scan = new Scanner(is);
			
			//input a valid number
			int choose = Integer.MIN_VALUE;
			while(choose == Integer.MIN_VALUE){
				try{
					choose = scan.nextInt();
				} catch (InputMismatchException ex){
					System.out.println("invalid value");
					scan.nextLine();
				}
			}
			
			//Choose operation, enter 1, 2, 3, 4, 5 to choose different function
			switch(choose)
			{
			//load file and output the original routing table
			case 1:
				Router.update(Router, null);
				lf.LoadFile(Router.Table);
				Router.update(Router, lf.temp);
				if(Router.Table != null&&Router.Table.size() != 0){
					//system prompt
					System.out.println("Original routing table is as follows:");
					for(int i = 0; i < lf.routerNumber; i++){
						for(int j = 0; j < lf.routerNumber; j++)
							System.out.printf("%4s",Router.Table.get(lf.routerNumber*i+j));
						System.out.println();
					}
				}
				else
					System.out.println("The original routing table has not been inputted.");
				break;
			//output a final table of a selected router
			case 2:
				if(Router.Table != null&&Router.Table.size() != 0){
					fs.Secleted(Router.Table, lf.routerNumber);
					System.out.println("The final table of this selected router is:");
					System.out.println(fs.selected);
				}
				else
					System.out.println("The original routing table has not been inputted.");
				break;
			//output a final table for all the routers
			case 3:
				if(Router.Table != null&&Router.Table.size() != 0){
					ft.RoutingTable(Router.Table, lf.routerNumber);
					System.out.println("Final routing table computed by Distance Vector algorithm is:");
					for(int i = 0; i < lf.routerNumber; i++){
						for(int j = 0; j < lf.routerNumber; j++)
							System.out.printf("%4s",ft.RoutingTable.get(lf.routerNumber*i+j));
						System.out.println();
					}
				}
				else
					System.out.println("The original routing table has not been inputted.");
				break;
			//fail a router and define the corresponding values to -1
			case 4:
				if(Router.Table != null&&Router.Table.size() != 0){
					lfail.FailLink(Router.Table, lf.routerNumber);
					Router.update(Router, lfail.failedRouterTable);
				}
				else
					System.out.println("The original routing table has not been inputted.");
				break;
			//find a shortest path of two routers
			case 5:
				if(Router.Table != null&&Router.Table.size() != 0)
					sp.Path(Router.Table, lf.routerNumber);
				else
					System.out.println("The original routing table has not been inputted.");
				break;
			default:
				break;
			}
		}
	}
}
