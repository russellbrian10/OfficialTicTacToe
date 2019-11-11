package ourTicTacToe;
import java.text.DecimalFormat;
import java.util.*;

public class Board3D {
	private Point[][][] board = new Point[4][4][4];
	public Board3D(){
//		DecimalFormat f = new DecimalFormat("##.00");
		//PLAYER ONE USES X'ES, PLAYER TWO USES O'S
		
		//each point should have numGamesPlayed property and numGamesWon property.  these will be used for reward function
		
		//populate board with values
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k] = new Point(0);
					board[i][j][k].util_val = (float) (Math.round(Math.random()*100.0)/100.0);
				}
			}
		}	
	}
	
	//update board is passed a boolean indicating which player made the move and three ints giving position of changed point
	public void updateBoard(boolean isP1, int x, int y, int z) {  
		if (board[x][y][z].state == 0) {   //if the square is blank
			if (isP1) {
				board[x][y][z].state = 1;
			} else {
				board[x][y][z].state = -1;
			}
		}

	}
	
	public Point[][][] getBoard() {
		return this.board;
	}
	
	public Point[] getXes() { // if p1 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
		ArrayList<Point> arrayListXes = new ArrayList<Point>();
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (board[i][j][k].state == 1) {
						arrayListXes.add(board[i][j][k]);
					}
				}
			}
		}
		Point[] finalArrayXes = (Point[]) arrayListXes.toArray(); //note: not tested yet
		return finalArrayXes;
		
	}
	
	public Point[] getOs() {  // if p2 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
		ArrayList<Point> arrayListOs = new ArrayList<Point>();
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (board[i][j][k].state == -1) {
						arrayListOs.add(board[i][j][k]);
					}
				}
			}
		}
		Point[] finalArrayOs = (Point[]) arrayListOs.toArray(); //note: not tested yet
		return finalArrayOs;
	}
	
	
	public void wipeBoard() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k].state = 0; //adjust so that points property of currentvalue is correct
					
				}
			}
		}
	}
	
	public void printBoard(){
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (i==0 && j==0 && k==0) System.out.println("----TOP PLANE----");
					if (i==1 && j==0 && k==0) System.out.println("--1ST MID PLANE--");
					if (i==2 && j==0 && k==0) System.out.println("--2ND MID PLANE--");
					if (i==3 && j==0 && k==0) System.out.println("---BOTTOM PLANE--");
					
					System.out.print(board[i][j][k].util_val + " "); 
					
				}
				System.out.println();
				if((i==0 && j==3) || (i==1 && j==3) || (i==2 && j==3) || (i==3 && j==3)) System.out.println("-----------------");
				System.out.println();
			}
		}
	}
	public void printBoardState(){
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (i==0 && j==0 && k==0) System.out.println("----TOP PLANE----");
					if (i==1 && j==0 && k==0) System.out.println("--1ST MID PLANE--");
					if (i==2 && j==0 && k==0) System.out.println("--2ND MID PLANE--");
					if (i==3 && j==0 && k==0) System.out.println("---BOTTOM PLANE--");
					
					System.out.print(board[i][j][k].state + " "); 
					
				}
				System.out.println();
				if((i==0 && j==3) || (i==1 && j==3) || (i==2 && j==3) || (i==3 && j==3)) System.out.println("-----------------");
				System.out.println();
			}
		}
	}
}
