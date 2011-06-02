import java.util.ArrayList;
import java.util.Random;

//Creates class.
public class Maze {

	//Fields!
	ArrayList<String> array1 = new ArrayList<String> ();
	int m;
	int n;	

	//Constructor.
	public Maze(int m, int n){
		
		this.m = m;
		this.n = n;
	}
	
	//This method initializes the maze using a string of ones and zeros.
	public void load(String maze){
		
		//Calculates the number of ones/zeros in the string.
		int w = (m * 2 + 1) * (n * 2 + 1);
		
		//For each one/zero in the string.
		for(int i = 0; i < w; i++){
			
			//Add the corresponing number as a string in an array of strings (field).
			array1.add(maze.substring(i, i + 1));
		}
		
		//Tester:
		//System.out.println(array1);
	}
	
	//This method prints a diagram of the maze on the console.
	public void display(){
		
		//Counter.
		int a = 1;
		
		//For every row of cells.
		for (int i = 0; i < n; i++){
			
			//For every column of cells.
			for (int j = 0; j < m; j++){
				
				//Always print first.
				System.out.print("+");
				
				//If the correspondent string is a "1".
				if (array1.get(a).equals("1")){
					
					System.out.print("-");
				
				//If the correspondent string is a "0".
				} else {
					
					System.out.print(" ");
				}
				
				//Increase counter to skip the "+".
				a += 2;
			}
			
			//Move counter to the corresponding position in the next row.
			a += 2;
			
			//Print final "+" of the row.
			System.out.println("+");
			
			//Print first "|" and " " of the next row.
			System.out.print("| ");
			
			//For every column (minus one cicle because is not needed).
			for (int k = 0; k < m - 1; k++){
				
				//If the correspondent string is a "1".
				if (array1.get(a).equals("1")){
					
					System.out.print("|");
				
				//If the correspondent string is a "0".
				} else {
					
					System.out.print(" ");
				}
				
				//Always print for cell space.
				System.out.print(" ");
				
				//Increase counter to skip the " ".
				a += 2;
			}
			
			//Move counter to the corresponding position in the next row.
			a += 2;
			
			//Print final "|" of the row.
			System.out.println("|");
		}
		
		//For every column of cells (prints final line).
		for (int l = 0; l < m; l++){
			
			System.out.print("+-");
		}
		
		//Prints final "+".
		System.out.println("+");
	}	
	
	//This method determines if there's a way to walk from a specified beginning 
	//position to a specified ending position.
	public boolean solve(int x1, int y1, int x2, int y2){
		
		//Copies initial point.
		int x = x1;
		int y = y1;
		
		//Initilizing arrays.
		ArrayList<String> direction = new ArrayList<String> ();
		
		ArrayList<Integer> record = new ArrayList<Integer> ();
		
		ArrayList<Integer> doublepath = new ArrayList<Integer> ();
		
		ArrayList<String> copy = new ArrayList<String> ();
		
		//Gets the initial position of the initial point in the main array (array1)
		//or its index, in other words.
		int position = ( (2 * m) + ( (2 * m + 1) * 2) * y) + (2 + 2 * x);
		
		//Records initial position.
		record.add(position);
		
		//Initializing variables.
		boolean jumper = true;
		
		int indicator = 0;
		
		int indicator2 = 0;
		
		int i = 0;
		
		//Runs as long as the current point doesn't match the final point.
		while (x != x2 || y != y2){
			
			//Tester:
			//System.out.println(position);
			
			//Used to skip statements when array "direction" doesn't need to be
			//modified.
			if (jumper == true){
				
				//Tester:
				//System.out.println("in");
				
				//Empties array "direction".
				direction.removeAll(direction);
				
				//If there is not a wall to the right of the cell.
				if (array1.get(position + 1).equals("0") && indicator != 2){
					
					//Add that posibility to the array "direction".
					direction.add("right");
				}
				
				//If there is not a wall to the left of the cell.
				if (array1.get(position - 1).equals("0") && indicator != 1){
					
					//Add that posibility to the array "direction".
					direction.add("left");
				}
				
				//If there is not a wall above the cell.
				if (array1.get(position - (2 * m + 1)).equals("0") && indicator != 4){
					
					//Add that posibility to the array "direction".
					direction.add("up");
				}
				
				//If there is not a wall below the cell.
				if (array1.get(position + (2 * m + 1)).equals("0") && indicator != 3){
					
					//Add that posibility to the array "direction".
					direction.add("down");
				}
				
				//Tester:
				//System.out.println(direction);
			}
			
			//If array "direction" is not empty.
			if (!direction.isEmpty()){
				
				//Random class.
				Random rand = new Random();
				
				//Get a random index from the direction "array".
				int randnumb = rand.nextInt(direction.size());
				
				//Tester:
				//System.out.println(randnumb);
				
				//If there is more than one object in the array "direction".
				if (direction.size() > 1){
					
					//Make a copy of the indicator.
					indicator2 = indicator;
					
					//Add that position to the "doublepath" array.
					doublepath.add(position);
				}
				
				//If random index points "right".
				if (direction.get(randnumb).equals("right")){
					
					//Increase:
					x++;
					
					//Set:
					indicator = 1;
					
				//If random index points "left".
				} else if (direction.get(randnumb).equals("left")){
					
					//Decrease:
					x--;
					
					//Set:
					indicator = 2;
				
				//If random index points "up".
				} else if (direction.get(randnumb).equals("up")){
					
					//Decrease:
					y--;
					
					//Set:
					indicator = 3;
					
				//If random index points "down".
				} else {
					
					//Increase:
					y++;
					
					//Set:
					indicator = 4;
				}
				
				//Gets value of new position.
				position = ( (2 * m) + ( (2 * m + 1) * 2) * y) + (2 + 2 * x);
				
				//Records new position.
				record.add(position);
				
				//Testers:
				//System.out.println(position);
				//System.out.println(x + " " + y);
				
				//If there is more than one object in the array "direction".
				if (direction.size() > 1){
					
					//If our "cursor" just came from the given position or it already
					//chose to follow that direction.
					if (indicator == 1 || indicator2 == 2){
						
						//Remove from array.
						direction.remove("right");
					
					//If our "cursor" just came from the given position or it already
					//chose to follow that direction.
					} else if (indicator == 2 || indicator2 == 1){
						
						//Remove from array.
						direction.remove("left");
					
					//If our "cursor" just came from the given position or it already
					//chose to follow that direction.
					} else if (indicator == 3 || indicator2 == 4){
						
						//Remove from array.
						direction.remove("up");
					
					//If our "cursor" just came from the given position or it already
					//chose to follow that direction.
					} else if (indicator == 4 || indicator2 == 3){
						
						//Remove from array.
						direction.remove("down");
					}
					
					//Adds in the array "copy" the single current object in "direction".
					copy.add(direction.get(0));
					
					//Testers:
					//System.out.println(doublepath);
					//System.out.println(direction);
					//System.out.println(copy);

					//Increase counter.
					i++;
				}
				
				//Set true.
				jumper = true;
			
			//If array "direction" is empty.
			} else {
				
				//If arrays "doublepath" and "copy" are not empty.
				if (!doublepath.isEmpty() && !copy.isEmpty()){
					
					//Gets the value of the position of the last cell that had two paths
					//to follow.
					int r = doublepath.get(doublepath.size() - 1);
					
					//Gets the x coordinate for the given position value.
					x = r % (m * 2 + 1) / 2;
					
					//Gets the y coordinate for the given position value.
					y = (r - (2 * m) - (2 + 2 * x)) / ((2 * m + 1) * 2);
					
					//Gets the new position value.
					position = ( (2 * m) + ( (2 * m + 1) * 2) * y) + (2 + 2 * x);
					
					//Records position.
					record.add(position);
					
					//Testers:
					//System.out.println(position);
					//System.out.println(x + " " + y);
					//System.out.println(copy);
					
					//Removes last position value of the array and eliminates option.
					doublepath.remove(doublepath.size() - 1);
					
					//Copies in "direction" the last object in "copy".
					direction.add(copy.get(copy.size() - 1));
					
					//Tester:
					//System.out.println(direction);
					
					//Removes the last object in the array and eliminates option.
					copy.remove(copy.size() - 1);
					
					//Set false.
					jumper = false;
				
				//If arrays "doublepath" and "copy" are empty.
				} else {
					
					//Didn't find the final position.
					return false;
				}
			}
		}
		
		//Program exited while loop because it found the final position.
		return true;
	}
	
	//This method is just like solve() but traces the positions that the solution visits.
	//Additional code includes comments. For the rest see method solve() for reference.
	public void trace(int x1, int y1, int x2, int y2){
		
		int x = x1;
		int y = y1;
		
		ArrayList<String> direction = new ArrayList<String> ();
		
		ArrayList<Integer> record = new ArrayList<Integer> ();
		
		ArrayList<Integer> doublepath = new ArrayList<Integer> ();
		
		ArrayList<String> copy = new ArrayList<String> ();
		
		int position = ( (2 * m) + ( (2 * m + 1) * 2) * y) + (2 + 2 * x);
		
		record.add(position);
		
		//Prints title.
		System.out.println("Position Trace:");
		
		//Prints initial position in (x, y) format.
		System.out.println("(" + x + ", " + y + ")");
		
		boolean jumper = true;
		
		int indicator = 0;
		
		int indicator2 = 0;
		
		int i = 0;
		
		while (x != x2 || y != y2){
			
			//Tester:
			//System.out.println(position);
			
			if (jumper == true){
				
				//Tester:
				//System.out.println("in");
				
				direction.removeAll(direction);
				
				if (array1.get(position + 1).equals("0") && indicator != 2){
					
					direction.add("right");
				}
				
				if (array1.get(position - 1).equals("0") && indicator != 1){
					
					direction.add("left");
				}
				
				if (array1.get(position - (2 * m + 1)).equals("0") && indicator != 4){
					
					direction.add("up");
				}
				
				if (array1.get(position + (2 * m + 1)).equals("0") && indicator != 3){
					
					direction.add("down");
				}
				
				//Tester:
				//System.out.println(direction);
			}
			
			if (!direction.isEmpty()){
				
				Random rand = new Random();
				
				int randnumb = rand.nextInt(direction.size());
				
				//Tester:
				//System.out.println(randnumb);
				
				if (direction.size() > 1){
					
					indicator2 = indicator;
					
					doublepath.add(position);
				}
				
				if (direction.get(randnumb).equals("right")){
					
					x++;
					indicator = 1;
					
				} else if (direction.get(randnumb).equals("left")){
					
					x--;
					indicator = 2;
					
				} else if (direction.get(randnumb).equals("up")){
					
					y--;
					indicator = 3;
					
				} else {
					
					y++;
					indicator = 4;
				}
				
				position = ( (2 * m) + ( (2 * m + 1) * 2) * y) + (2 + 2 * x);
				
				record.add(position);
				
				//Testers:
				//System.out.println(position);
				//System.out.println(x + " " + y);
				
				//Prints current position in (x, y) format.
				System.out.println("(" + x + ", " + y + ")");
				
				if (direction.size() > 1){
					
					if (indicator == 1 || indicator2 == 2){
						
						direction.remove("right");
						
					} else if (indicator == 2 || indicator2 == 1){
						
						direction.remove("left");
						
					} else if (indicator == 3 || indicator2 == 4){
						
						direction.remove("up");
						
					} else if (indicator == 4 || indicator2 == 3){
						
						direction.remove("down");
					}
					
					copy.add(direction.get(0));
					
					//Testers:
					//System.out.println(doublepath);
					//System.out.println(direction);
					//System.out.println(copy);

					i++;
				}
				
				jumper = true;
			
			} else {
				
				if (!doublepath.isEmpty() && !copy.isEmpty()){
					
					int r = doublepath.get(doublepath.size() - 1);
					
					x = r % (m * 2 + 1) / 2;
					
					y = (r - (2 * m) - (2 + 2 * x)) / ((2 * m + 1) * 2);
					
					position = ( (2 * m) + ( (2 * m + 1) * 2) * y) + (2 + 2 * x);
					
					record.add(position);
					
					//Prints current position in (x, y) format.
					System.out.println("(" + x + ", " + y + ")");
					
					//Testers:
					//System.out.println(position);
					//System.out.println(x + " " + y);
					//System.out.println(copy);
					
					doublepath.remove(doublepath.size() - 1);
					
					direction.add(copy.get(copy.size() - 1));
					
					//Tester:
					//System.out.println(direction);
					
					copy.remove(copy.size() - 1);
					
					jumper = false;
					
				} else {
					
					//Didn't find the final position.
					System.out.println("There is no way to the final cell.");
					
					//Leave loop.
					break;
				}
			}
		}
		
		//Always printed.
		System.out.println("END");		
	}
	
	//This method resets all the cells and walls and comes up with a random new maze of 
	//the same size.
	public void redesign(){
		
		//Initializing variables for use in method.
		int a = m * 2 + 3;
		
		int b = n;
		
		int c = m - 1;
		
		int d = m;
		
		String e;
		
		//Random class.
		Random rand = new Random();
		
		//For each row.
		for (int i = 0; i < b; i++){
			
			//For each column (minus one cycle, not needed).
			for (int j = 0; j < c; j++){
				
				//Saves random value 0 or 1.
				int randnumb = rand.nextInt(2);
				
				//This if statement gives "e" its corresponding string according to the 
				//random result.
				if (randnumb == 1){
					
					e = "1";
					
				} else {
					
					e = "0";
				}
				
				//If all sides of the cell have a wall.
				if (array1.get(a - 2).equals("1") && array1.get(a - (2 * m + 2)).equals("1") && array1.get(a + (2 * m)).equals("1")){
				
					//Tester:
					//System.out.println(i + " " + j + " " + "A");
					
					//Leave the remaining side open.
					array1.set(a, "0");
				
				//If all sides of the cell are open.
				} else if (array1.get(a - 2).equals("0") && array1.get(a - (2 * m + 2)).equals("0") && array1.get(a + (2 * m)).equals("0")){
					
					//Tester:
					//System.out.println(i + " " + j + " " + "B");
					
					//Put a wall in the remaining side.
					array1.set(a, "1");
					
				} else {
					
					//Tester:
					//System.out.println(i + " " + j + " " + "C");
					
					//Set the random string "1" or "0" to index a.
					//Choose randomly to close or leave open the given side.
					array1.set(a, e);
				}
				
				//Increase index to skip " ".
				a += 2;	
			}
			
			//Move index to the next line.
			a += 2;
			
			//If index reached the last modifiable line.
			if (i == (b - 1)){
				
				//Exit loop.
				break;
			}
			
			//For each column.
			for (int k = 0; k < d; k++){
				
				//Saves random value 0 or 1.
				int randnumb = rand.nextInt(2);
				
				//This if statement gives "e" its corresponding string according to the 
				//random result.
				if (randnumb == 1){
					
					e = "1";
					
				} else {
					
					e = "0";
				}
				
				//If all sides of the cell have a wall.
				if (array1.get(a - (m * 2)).equals("1") && array1.get(a - (m * 2 + 2)).equals("1") && array1.get(a - (2 * m + 1) * 2).equals("1")){
					
					//Tester:
					//System.out.println(i + " " + k + " " + "D");
					
					//Leave the remaining side open.
					array1.set(a, "0");
				
				//If all sides of the cell are open.
				} else if (array1.get(a - (m * 2)).equals("0") && array1.get(a - (m * 2 + 2)).equals("0") && array1.get(a - (2 * m + 1) * 2).equals("0")){
					
					//Tester:
					//System.out.println(i + " " + k + " " + "E");
					
					//Put a wall in the remaining side.
					array1.set(a, "1");
					
				} else {
					
					//Tester:
					//System.out.println(i + " " + k + " " + "F");
					
					//Set the random string "1" or "0" to index a.
					//Choose randomly to close or leave open the given side.
					array1.set(a, e);
				}
				
				//Increase index to skip "+".
				a += 2;	
			}
			
			//Move index to the next line.
			a += 2;
		}
	}
}	

