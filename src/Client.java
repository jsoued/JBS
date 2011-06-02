
//Client Program.
public class Client {

	//Includes calls for all of the methods to test them.
	public static void main (String args[]){
		
		Maze maze1 = new Maze(4,4);
		
		maze1.load("111111111100010001111010101100010101101110101100000101111011101100000101111111111");
		
		maze1.display();
		
		System.out.println();
		
		boolean zen = maze1.solve(0, 0, 2, 3);
		
		System.out.println(zen); 
		
		System.out.println();
		
		maze1.trace(0, 0, 2, 2);
		
		System.out.println();
		
		maze1.redesign();
		
		maze1.display();
		
		maze1.trace(0, 0, 3, 3);
	}
}
