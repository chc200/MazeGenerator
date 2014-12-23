import java.util.Random;

public class Maze{
	String[][] mazeArray;
	int col;
	int row;
	int wall1;
	int wall2;
	int wall3;
	int count=0;
	boolean solved=false;

	
	public Maze(int width, int height)
	{
		this.col=width*2;
		this.row=height*2;
		
		mazeArray = new String[row+1][col+1]; 	// row -- column

		for(int top=0; top<col+1; top++)
		{
			if(top%2!=0)
				mazeArray[0][top]="---";
			else
				mazeArray[0][top]="+";
		}

		for(int bottom=0; bottom<col+1; bottom++)
		{
			if(bottom%2!=0)
				mazeArray[row][bottom]="---";
			else
				mazeArray[row][bottom]="+";
		}


		for(int left=0; left<row; left++)
		{
			if(left%2!=0)
				mazeArray[left][0]="|";
			else
				mazeArray[left][0]="+";
		}

		for(int right=0; right<row; right++)
		{
			if(right%2!=0)
				mazeArray[right][col]="|";
			else
				mazeArray[right][col]="+";
		}

		for(int i=1; i<row; i++)
		{
			if(i%2==0)
			{
				for(int c=1; c<col; c++)
				mazeArray[i][c]="+";//+
			}
		}

		makeClear();

		makeMaze(0,0 ,col, row); // int col, int row, int startX,int startY,int finishX, int finishY

		showMaze();
	}

	private void makeMaze(int startX,int startY,int finishX, int finishY)
	{
		if(finishX- startX >2 && finishY- startY >2)
		{
			int midCol= (startX + finishX)/2;
			int midRow= (startY + finishY)/2;

			if(midRow%2!=0)
			midRow++;
			if(midCol%2!=0)
			midCol++;
	
		for(int r=startY; r<finishY; r++)
		{
			if(r%2!=0)
			{
				mazeArray[r][midCol]="|";
			}
			if(r%2==0)
			{
				mazeArray[r][midCol]="+";
			}
		}

		for(int c=startX; c<finishX; c++)
		{
			if(c%2!=0)
			{
				mazeArray[midRow][c]="---";
			}
			if(c%2==0)
			{
				mazeArray[midRow][c]="+";
			}
		}

		Random rn = new Random();
		wall1 = rn.nextInt(4);
		wall2 = rn.nextInt(4);
		wall3 = rn.nextInt(4);
		int remove=0;

			while(wall1 == wall2|| wall1== wall3|| wall2==wall3)
			{
				wall1 = rn.nextInt(4);
				wall2 = rn.nextInt(4);
				wall3 = rn.nextInt(4);
			}
		

		if(wall1 == 0 || wall2 == 0|| wall3 == 0)//north
		{
			remove=rn.nextInt(midRow-startY)+startY;//startY to midpointY
			while(remove ==startY|| remove%2==0 || remove == midRow)
			remove =rn.nextInt(midRow-startY)+startY;
			

			mazeArray[remove][midCol]=" ";
		}


		if(wall1 == 1 || wall2 == 1 || wall3 == 1)//east midpointX to edgeX
		{
			remove = rn.nextInt(finishX-midCol)+midCol;
			
			while(remove ==finishX || remove%2==0 || remove == midCol)
				remove = rn.nextInt(finishX-midCol)+midCol;

			mazeArray[midRow][remove]="   ";

		}
		if(wall1 == 2 || wall2 == 2 || wall3 == 2)//sout
		{	
				remove = rn.nextInt(finishY-midRow)+midRow; //midpointY to edgeY
		
			while(remove ==midRow || remove%2==0 || remove == finishY)
				remove = rn.nextInt(finishY-midRow)+midRow;

			mazeArray[remove][midCol]=" ";

		}
		if(wall1 == 3 || wall2 == 3 || wall3 == 3) //west
		{
				remove = rn.nextInt(midCol-startX)+startX; //edgeX to midpoint
			while(remove ==finishX || remove%2==0 || remove == midCol)
				remove = rn.nextInt(midCol-startX)+startX;

			mazeArray[midRow][remove]="   ";
		}

		makeMaze(startX, startY, midCol,midRow);// top left startX, startY, midCol, midRow
		makeMaze(midCol, startY, finishX,midRow ); //top right ,midCol, StartY,col, midRow
		makeMaze(startX, midRow, midCol,finishY );//bottom right, startY, midRow, midCOl, row
		makeMaze(midCol, midRow, finishX,finishY );// bottom left, midCol,midRow,col,row
	}
		else
		return;
	}


	public boolean solveMaze(int sx, int sy, int ex, int ey, int px, int py)
	{
		count++;
		if(count == 1)
			mazeArray[sy][sx]=" * ";
		
		boolean validMove;

		if(sx==ex && sy==ey)
		{
			//System.out.println("Target Reached");
			return true;
		}
		else
		{

/*										NORTH CONDITION 										*/
			if(sy-1>=0)
			{
//System.out.println("NORTH 1");
//System.out.println("SX: "+ sx+ "SY: "+ sy);
				
				if(sx%2==0)
				{
					if((!mazeArray[sy-1][sx].equals("+")) && !(sx== px && sy-1==py))//north east south west
					{	
						//System.out.println("NORTH +");
						
						if(solveMaze(sx,sy-1,ex,ey,sx,sy))
						{
							mazeArray[sy-1][sx]=" * ";
							return true;
						}


					}
				}
				else
				{
					if((!mazeArray[sy-1][sx].equals("---")) && !(sx== px && sy-1==py))//north east south west
					{	
						//System.out.println("NORTH ---");
						if(solveMaze(sx,sy-1,ex,ey,sx,sy))
						{
							mazeArray[sy-1][sx]=" * ";
							return true;

						}
					}
				}
			}
/*							EAST CONDITION												*/

			if(sx+1<=col) 
			{
//System.out.println("EAST 1");
//System.out.println("SX: "+ sx+ "SY: "+ sy);
				
				if(sy%2==0)
				{
					if((!mazeArray[sy][sx+1].equals("+")) && !(sx+1== px && sy==py))//north east south west
					{	
						//System.out.println("EAST +");
						if(solveMaze(sx+1,sy,ex,ey,sx,sy))
						{
							mazeArray[sy][sx+1]="*"; // **
							return true;
						}
					}
				}
				else
				{
					if((!mazeArray[sy][sx+1].equals("|")) && !(sx+1== px && sy==py))//north east south west
					{	
						//System.out.println("EAST ---");
						if(solveMaze(sx+1,sy,ex,ey,sx,sy))
						{
							mazeArray[sy][sx+1]="* "; //**
							return true;
						}
					}
				}
			}

/*										SOUTH CONDITION 										*/
			if(sy+1<=row)  
			{
//System.out.println("SOUTH 1");
//System.out.println("SX: "+ sx+ "SY: "+ sy);
				
				if(sx%2==0)
				{
					if((!mazeArray[sy+1][sx].equals("+")) && !(sx== px && sy+1==py))//north east south west
					{	
						//System.out.println("SOUTH +");
						if(solveMaze(sx,sy+1,ex,ey,sx,sy))
						{
							mazeArray[sy+1][sx]=" * ";
							return true;
						}
					}
				}
				else
				{
					if((!mazeArray[sy+1][sx].equals("---")) && !(sx== px && sy+1==py))//north east south west
					{	
						//System.out.println("SOUTH ---");
						if(solveMaze(sx,sy+1,ex,ey,sx,sy))
						{
							mazeArray[sy+1][sx]=" * ";
							return true;
						}
					}
				}
			}
/*							WEST CONDITION												*/

			if(sx-1>=0) 
			{
//System.out.println("WEST 1");
//System.out.println("SX: "+ sx+ "SY: "+ sy);
				
				if(sy%2==0)
				{
					if((!mazeArray[sy][sx-1].equals("+")) && !(sx-1== px && sy==py))//north east south west
					{	
						//System.out.println("WEST +");
						if(solveMaze(sx-1,sy,ex,ey,sx,sy))
						{
							mazeArray[sy][sx-1]="*"; //**
							return true;
						}
					}
				}
				else
				{
					if((!mazeArray[sy][sx-1].equals("|")) && !(sx-1== px && sy==py))//north east south west
					{	
						//System.out.println("WEST ---");
						if(solveMaze(sx-1,sy,ex,ey,sx,sy))
						{
							mazeArray[sy][sx-1]=" *"; // **
							return true;
						}
					}
				}
			}
			return false;
		}
	}

	private void makeClear()
	{
		for(int m=1; m<row;m++)	
			for(int i=1; i<col;)
			{
				if(i%2!=0)
				mazeArray[m][i]="   ";
				else
				mazeArray[m][i]=" ";
				i++;
			}
	}

	public void showMaze()
	{
		for(int i = 0; i<row+1; i++)
		{
			for(int n=0; n<col+1; n++)
			{
				System.out.print(mazeArray[i][n]);
			}
			System.out.println();
		}

	}
}