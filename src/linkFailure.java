import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Huaxia Wang
 *
 */

public class linkFailure {
	
	private static Scanner scan;
	
	ArrayList<Integer> failedRouterTable = new ArrayList<Integer>();

	public void FailLink(ArrayList<Integer> a, int size){
		
		System.out.println("Please input the number of failed router:");
		
		InputStream is = System.in;
		scan = new Scanner(is);
		
		//input the failed router number
		int failedRouter = Integer.MIN_VALUE;
		while(failedRouter == Integer.MIN_VALUE){
			try{
				failedRouter = scan.nextInt();
			} catch (InputMismatchException ex){
				System.out.println("invalid value, input again:");
				scan.nextLine();
			}
		}
		//input a valid value
		while(failedRouter > size|| failedRouter < 1){
			System.out.println("Invalid value, input again:");
			failedRouter = scan.nextInt();
		}
		
		//set routing table's values which is related to failed router to -1
		for(int i = 0; i < size; i++){
			a.set((failedRouter - 1)*size + i, -1);
			a.set(i*size + failedRouter - 1, -1);
		}
		
		for(int i = 0; i < size*size; i++)
			failedRouterTable.add(a.get(i));
		
		System.out.println("Router failed.");
			
	}
}
