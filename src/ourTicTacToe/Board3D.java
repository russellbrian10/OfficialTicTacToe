package ourTicTacToe;

public class Board3D {
	private Point[][][] board = new Point[4][4][4];
	public Board3D(){
		
		//PLAYER ONE USES X'ES, PLAYER TWO USES O'S
		
		//each point should have numGamesPlayed property and numGamesWon property.  these will be used for reward function
		
		//populate board with values
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k] = new Point();
					
				}
			}
		}
			
	}
	
	//update board is passed a boolean indicating which player made the move and three ints giving position of changed point
	public void updateBoard(boolean isP1, int x, int y, int z) {  
		if (board[x][y][z].value == null) {   //if the square is blank
			if (isP1) {
				board[x][y][z].value = "X";
			} else {
				board[x][y][z].value = "O";
			}
		}

	}
	
	public Point[][][] getBoard() {
		return this.board;
	}
	
	public Point[] getXes() { // if p1 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
		
	}
	
	public Point[] getOs() {  // if p2 wins, used by solver to update numGamesPlayed and numGamesWon for each individual point
		
	}
	
	
	public void wipeBoard() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k].value = null; //adjust so that points property of currentvalue is correct
					
				}
			}
		}
	}
	
	//minor change

}
