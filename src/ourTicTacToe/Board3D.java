package ourTicTacToe;
import java.util.*;

public class Board3D {
	private Point[][][] board = new Point[4][4][4];
	public Board3D(){

		//PLAYER ONE USES X'ES, PLAYER TWO USES O'S
				
		//populate board with values
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k] = new Point(0,i,j,k); 
				}
			}
		}	
	}
	
	/*
	 * Receives a boolean indicating which player made the move and three ints giving position of changed point
	 * Returns the state of the GAME, 0 if game still going, 1 if a player won, 2 if there is a draw
	 */
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
		
		
		// Checks if all spaces have been played or one of the two players has won a game.
		int wonValue = checkIfWon(x,y,z);
		if (wonValue == 0) {
			if (checkIfOverNoWin()) {
				wonValue=2;
			}
		}
		return wonValue;

	}
	
	/*
	 * Returns 1 if P1 has won, -1 if P2 has won, 0 if no one has won yet
	 */
	public int checkIfWon(int x,int y,int z) {		// 
		int potentialWinner = board[x][y][z].state;
		
		boolean straight1 = true, straight2 = true, straight3 = true;
		boolean diagonal1 = true, diagonal2 = true, diagonal3 = true, diagonal4 = true, diagonal5 = true, diagonal6 = true;
		boolean diagonal7 = true, diagonal8 = true, diagonal9 = true, diagonal10 = true;
		
		//iterate through all possible lines of victory 
		for (int i=0; i<4; i++) {
			// if all points don't belong to potentialWinner, then this player didn't win through that line
			if (board[i][y][z].state != potentialWinner) {straight1 = false;} 
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
		
		//If the potentialWinner has 4 in a line then we return the int associated with that player
		if (straight1 || straight2 || straight3 || diagonal1 || diagonal2 || diagonal3 || diagonal4 || diagonal5 || //if one of the rows has 4 in a row
				diagonal6 || diagonal7 || diagonal8 || diagonal9 || diagonal10) {
			return potentialWinner;
		} else {
			return 0;
		}	
	}
	
	/*
	 * Checks if the game ended but no one won.
	 * Checks every Point to see if occupied
	 */
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
	
	/*
	 *  If P1 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
	 */
	public ArrayList<Point> getXes() { 
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
		return arrayListXes;
		
	}
	
	/*
	 *  If P2 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
	 */
	public ArrayList<Point> getOs() {  
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
		return arrayListOs;
	}
	
	/*
	 *  Returns the Points that are blank. Needed for when the player does exploration. Don't want to use occupied Points.
	 */
	public ArrayList<Point> getBlanks() {  
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
		return arrayListBlanks;
	}
	
	/*
	 * Wipes the board by resetting the state of each Point to 0 (blank). wipeBoard() is called after each game
	 */
	public void wipeBoard() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k].state = 0; //adjust so that points property of currentvalue is correct
					
				}
			}
		}
	}
	
	/*
	 * Wipes the board and also resets the utility value of the Points.
	 */
	public void wipeBoardWipeUtility() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k].state = 0; //adjust so that points property of currentvalue is correct
					board[i][j][k].num_games = 1;
					board[i][j][k].num_wins = 0;
				}
			}
		}
	}
	
	/*
	 * Prints the utility values of all Points
	 */
	public void printBoard(){
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (i==0 && j==0 && k==0) System.out.println("-----TOP PLANE-----");
					if (i==1 && j==0 && k==0) System.out.println("---1ST MID PLANE---");
					if (i==2 && j==0 && k==0) System.out.println("---2ND MID PLANE---");
					if (i==3 && j==0 && k==0) System.out.println("----BOTTOM PLANE---");
					
					System.out.print(Math.round(board[i][j][k].getUtilValue()*1000.0)/1000.0 + " "); 
					
				}
				System.out.println();
				if((i==0 && j==3) || (i==1 && j==3) || (i==2 && j==3) || (i==3 && j==3)) System.out.println("-------------------");
				System.out.println();
			}
		}
	}
	
	/*
	 * Prints the state of all Points. -1, 0 or 1
	 */
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
