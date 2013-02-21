import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Huaxia Wang
 *
 */

//ArrayList Table
public class loadfile {

	private static BufferedReader in;

	//Count the number of rows and columns
	public int routerNumber = 0;
	
	//Store the txt file in a arraylist temporarily
	ArrayList<Integer> temp = new ArrayList<Integer>();

	//load file and output the original file
	public void LoadFile(ArrayList<Integer> a){
		//system prompt
		System.out.println("Please load orginal routing table data file:");
		
		InputStream is = System.in;
		Scanner scan = new Scanner(is);
		
		//initialize routerNumber and arraylist temp
		routerNumber = 0;
		temp.clear();
		//read file
		try{
			//FileReader file = new FileReader("testfile.txt");
			FileReader file = new FileReader(scan.nextLine());
			in = new BufferedReader(file);
			String line;
			//the line is not empty
			while((line=in.readLine())!=null){
				//every element in the txt file is separate by blank
				for(int i = 0; i < line.split(" ").length; i++){
					temp.add(Integer.parseInt(line.split(" ")[i]));
				}
				//count the number of routers
				routerNumber++;
			}
			a = temp;
		} catch(FileNotFoundException e){
			System.out.println("There is no such file.");
		} catch(IOException e){
			System.out.println("Invalid input.");
		}
	}
}
