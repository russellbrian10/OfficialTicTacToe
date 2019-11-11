package ourTicTacToe;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver {
	public int temp = 600;
	public int temp2 = 10000;
	
	public static void main(String[] args) throws FileNotFoundException 
	{ 
		Scanner sc = new Scanner(System.in);
		int trials1 = sc.nextInt();
		int trials2 = sc.nextInt();
		int trials3 = sc.nextInt();
		
		if ((trials1 < 0 & trials2 < 0 & trials3 < 0) || trials1 > trials2 || trials2 > trials3){
			System.out.println("Error in input. Try again");
			System.exit(0);
		}
		
		Board3D board3D = new Board3D();
		//print out a board with utility values after a number of trials (trials1, trials2, trials3)
		Point[][][] board = board3D.getBoard();
		
		board3D.printBoard();
		
	}
}
