import java.util.*;
import java.io.*;

public class MazeTester{
	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		Scanner ss = new Scanner(System.in);


		System.out.print("height: ");
		int height =sc.nextInt();
		System.out.print("width: ");
		int width = sc.nextInt();

		Maze ourMaze = new Maze(width,height);

		System.out.print("Solve Maze yes or no?: ");
		String yn=ss.nextLine();

		if(yn.equals("yes"))
		{
			System.out.print("Starting x: ");
			int startx = sc.nextInt();
			System.out.print("Starting y:");
			int starty=sc.nextInt();
			System.out.print("End x:");
			int endx=sc.nextInt();
			System.out.print("End y:");
			int endy=sc.nextInt();


			ourMaze.solveMaze(startx*2+1,starty*2+1,endx*2+1,endy*2+1,-1,-1);

			ourMaze.showMaze();
		}




	}

}