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
					board[i][j][k] = new Point(0,i,j,k);
				}
			}
		}	
	}
	
	//update board is passed a boolean indicating which player made the move and three ints giving position of changed point
	public int updateBoard(boolean isP1, int x, int y, int z) {  // returns -1 if p2(O's) has won, 0 if game is still ongoing, 1 if p1(X's) has won, and 2 if the game is over but no won has won
		if (board[x][y][z].state == 0) {   //if the square is blank
			if (isP1) {
				board[x][y][z].state = 1;
			} else {
				board[x][y][z].state = -1;
			}
		} else{
			System.out.println("This shouldn't be printing ");
		}
		
		// checks if all spaces have been played or one of the two players has won a game.
		int wonValue = checkIfWon(x,y,z);
		if (wonValue == 0) {
			if (checkIfOverNoWin()) {
				wonValue=2;
			}
		}
		return wonValue;

	}
	
	public int checkIfWon(int x,int y,int z) {		// returns 1 if p1 has won, -1 if p2 has won, 0 if no one has won yet
		int potentialWinner = board[x][y][z].state;
		
		boolean straight1 = true, straight2 = true, straight3 = true;
		boolean diagonal1 = true, diagonal2 = true, diagonal3 = true, diagonal4 = true, diagonal5 = true, diagonal6 = true;
		boolean diagonal7 = true, diagonal8 = true, diagonal9 = true, diagonal10 = true;
		
		//iterate through all possible lines of victory 
		for (int i=0; i<4; i++) {
			if (board[i][y][z].state != potentialWinner) {straight1 = false;} // if one of the values in this line is not potentialwinner, th
			if (board[x][i][z].state != potentialWinner) {straight2 = false;}
			if (board[x][y][i].state != potentialWinner) {straight3 = false;}
			
			// diagonal rows with winning potential
			if (board[i][i][z].state != potentialWinner) {diagonal1 = false;}
			if (board[i][3-i][z].state != potentialWinner) {diagonal2 = false;}
			
			if (board[i][y][i].state != potentialWinner) {diagonal3 = false;}
			if (board[i][y][3-i].state != potentialWinner) {diagonal4 = false;}
			
			if (board[x][i][i].state != potentialWinner) {diagonal5 = false;}
			if (board[x][3-i][i].state != potentialWinner) {diagonal6 = false;}
			
			//megadiagonals
			if (board[i][i][i].state != potentialWinner) {diagonal7 = false;}
			if (board[i][i][3-i].state != potentialWinner) {diagonal8 = false;}
			if (board[i][3-i][i].state != potentialWinner) {diagonal9 = false;}
			if (board[3-i][i][i].state != potentialWinner) {diagonal10 = false;}
		}
		if (straight1 || straight2 || straight3 || diagonal1 || diagonal2 || diagonal3 || diagonal4 || diagonal5 || //if one of the rows has 4 in a row
				diagonal6 || diagonal7 || diagonal8 || diagonal9 || diagonal10) {
			return potentialWinner;
		} else {
			return 0;
		}	
	}
	
	public boolean checkIfOverNoWin() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if(board[i][j][k].state == 0) {
						return false;
					}
				}
			}
		}
		return true;
		
	}
	
	public Point[][][] getBoard() {
		return this.board;
	}
	
	public ArrayList<Point> getXes() { // if p1 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
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
//		Point[] finalArrayXes = (Point[]) arrayListXes.toArray(); //note: not tested yet
		return arrayListXes;
		
	}
	
	public ArrayList<Point> getOs() {  // if p2 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
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
//		Point[] finalArrayOs = (Point[]) arrayListOs.toArray(); //note: not tested yet
		return arrayListOs;
	}
	
	public ArrayList<Point> getBlanks() {  // if p2 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
		ArrayList<Point> arrayListBlanks = new ArrayList<Point>();
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (board[i][j][k].state == 0) {
						arrayListBlanks.add(board[i][j][k]);
					}
				}
			}
		}
//		Point[] finalArrayOs = (Point[]) arrayListOs.toArray(); //note: not tested yet
		return arrayListBlanks;
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
					
					System.out.print(Math.round(board[i][j][k].getUtilValue()*100.0)/100.0 + " "); 
					
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
					
					System.out.print(board[i][j][k].getState() + " "); 
					
				}
				System.out.println();
				if((i==0 && j==3) || (i==1 && j==3) || (i==2 && j==3) || (i==3 && j==3)) System.out.println("-----------------");
				System.out.println();
			}
		}
	}
}
