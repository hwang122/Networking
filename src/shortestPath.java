import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * @author Huaxia Wang
 *
 */

public class shortestPath{
	
	private static final int infinity = 10000;
	ArrayList<Integer> temp = new ArrayList<Integer>();
	ArrayList<Integer> path = new ArrayList<Integer>();
	ArrayList<Integer> Bpath = new ArrayList<Integer>();
	
	public void Path(ArrayList<Integer> a, int size)throws IOException{
		
		int[] next = new int[size*size];
		//system prompt
		System.out.println("Please input the sourse and destination router number(bypass router number):");
		//define a buffer to store the input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] routerNumber = new String[3];
		//the source, destination and bypass is separate by blank
		routerNumber = input.split(" "); 
		//initialize source, destination and bypass
		int source = -1;
		int destination = -1;
		int bypass = -1;
		while(routerNumber.length < 2){
			System.out.println("Invalid value, Input again:");
			br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
			routerNumber = new String[3];
			routerNumber = input.split("\\s"); 
		}
		source = Integer.parseInt(routerNumber[0]);
		destination = Integer.parseInt(routerNumber[1]);
		if(routerNumber.length > 2)
			bypass = Integer.parseInt(routerNumber[2]);
		
		//validate value of source, destination and bypass
		while(bypass==-1?source>size||source<1||destination>size||destination<1:
			source>size||source<1||destination>size||destination<1||bypass>size||bypass<1){
			System.out.println("Invalid value, input again:");
			br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
			routerNumber = new String[3];
			//the source, destination and bypass is separate by blank
			routerNumber = input.split(" "); 
			//initialize source, destination and bypass
			source = Integer.parseInt(routerNumber[0]);
			destination = Integer.parseInt(routerNumber[1]);
			bypass = -1;
			if(routerNumber.length>2)
				bypass = Integer.parseInt(routerNumber[2]);
		}
		
		
		//initialize arraylist temp, path and path1, path2
		temp.clear();
		path.clear();
		Bpath.clear();
		
		for(int i = 0; i < size*size; i++)
			temp.add(a.get(i));
		
		//define -1 to be infinity
		for(int i = 0; i < size*size; i++)
			if(temp.get(i) < 0){
				temp.set(i, infinity);
			}
		
		//initialize next
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				next[i*size+j] = i+1;
		
		//use BF algorithm to compute the path
		for(int n = 1; n < size; n++)
			for(int v = 0; v < size; v++)
				for(int i = 0; i < size; i++)
					for(int j = 0; j < size; j++){
						if(temp.get(v*size + i) > (a.get(v*size + j)<0?infinity:a.get(v*size + j)) + temp.get(j*size + i)){
							temp.set(v*size + i, a.get(v*size + j) + temp.get(j*size + i));
							next[v*size+i] = j+1;
						}
					}

		//decide whether there is a path
		if(temp.get((source-1)*size+destination-1) >= infinity)
			System.out.println("There is no path between "+ source + " and " + destination+".");
		else{	
			//print the path
			//there is no bypass router
			if(bypass == -1){
				int start = source;
				int end = destination;
				while(end != next[(start-1)*size+end-1]){
					path.add(start);
					if(start == next[(start-1)*size+end-1])
						break;
					start = next[(start-1)*size+end-1];
				}
				System.out.print("The shortest path from " + source + " to " + destination + " is ");
				for(int i = 0; i <= path.size()-1; i++)
					System.out.print(path.get(i) + "-->");
				System.out.print(destination + ", the total cost is " + temp.get((source-1)*size+destination-1));
				System.out.println();
			}
			//there is a bypass router
			else{
				int start = source;
				int middle = bypass;
				int end = destination;
				
				if(temp.get((source-1)*size+bypass-1)+temp.get((bypass-1)*size+destination-1)>=infinity){
					System.out.println("There is no path between "+ source + " and " + destination+".");
				}
				else{
					//from source to bypass
					while(middle != next[(start-1)*size+middle-1]){
						Bpath.add(start);
						if(start == next[(start-1)*size+middle-1])
							break;
						start = next[(start-1)*size+middle-1];
					}
				
					//from bypass to destination
					while(end != next[(middle-1)*size+end-1]){
						Bpath.add(middle);
						if(middle == next[(middle-1)*size+end-1])
							break;
						middle = next[(middle-1)*size+end-1];
					}
					System.out.print("The shortest path from "+ source +" to "+ destination +" (bypass "+bypass+")is ");
					for(int i = 0; i <= Bpath.size()-1; i++)
						System.out.print(Bpath.get(i) + "-->");
					System.out.print(destination + ", the total cost is " + (temp.get((source-1)*size+bypass-1)+temp.get((bypass-1)*size+destination-1)));
					System.out.println();
				}
			}
		}
	}

}
