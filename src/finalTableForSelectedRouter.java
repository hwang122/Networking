import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Huaxia Wang
 *
 */

public class finalTableForSelectedRouter {
	
	private static Scanner scan;
	private static final int infinity = 10000;
	
	//To store the routing table of selected router
	ArrayList<Integer> selected = new ArrayList<Integer>();
	ArrayList<Integer> temp = new ArrayList<Integer>();
	
	public void Secleted(ArrayList<Integer> a, int size){
		System.out.println("Please input a router number:");
		InputStream is = System.in;
		scan = new Scanner(is);
		
		//input the selected router number
		int selectNumber = Integer.MIN_VALUE;
		while(selectNumber == Integer.MIN_VALUE){
			try{
				selectNumber = scan.nextInt();
			} catch (InputMismatchException ex){
				System.out.println("invalid value, input again:");
				scan.nextLine();
			}
		}
		//input a vaild value
		while(selectNumber > size|| selectNumber < 1){
			System.out.println("Invalid value, input again:");
			selectNumber = scan.nextInt();
		}
		
		//Define -1 to be infinity
		for(int i = 0; i < size*size; i++)
			if(a.get(i) < 0)
				a.set(i, infinity);
		
		//Clear the arraylist for another selected router number
		temp.clear();
		selected.clear();
		
		//initialize the arraylist
		for(int i = 0; i < size*size; i++)
			temp.add(a.get(i));
		
		//compute the shortest distance
		for(int n = 1; n < size; n++){
			for(int v = 0; v < size; v++)
				for(int i = 0; i < size; i++)
					for(int j = 0; j < size; j++){
						if(temp.get(v*size + i) > (a.get(v*size + j)<0?infinity:a.get(v*size + j)) + temp.get(j*size + i)){
							temp.set(v*size + i, a.get(v*size + j) + temp.get(j*size + i));
						}
					}
		}

		//define the selected router's routing table
		for(int i = 0; i < size; i++)
			selected.add(temp.get((selectNumber - 1)*size + i));
		
		//Redefine infinity to -1
		for(int i = 0; i < size; i++)
			if(selected.get(i) >= infinity)
				selected.set(i, -1);
	}

}
