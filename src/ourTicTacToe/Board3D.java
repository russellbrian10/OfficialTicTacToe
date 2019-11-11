package ourTicTacToe;

public class Board3D {
	private Point[][][] board = new Point[4][4][4];
	public Board3D(){
		
		//populate board with values
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k] = new Point();
	
				}
			}
		}
			
	}
	String hiString = "he";
	String hiString2 = "hello2";
	
	//update board is passed a boolean indicating which player made the move and three ints giving position of changed point
	public void updateBoard(boolean isP1, int x, int y, int z) {  
		if (board[x][y][z].value == 0) {   //if the square is blank
			
		}
		//board[x][y][z].

	}
	
	public Point[][][] getBoard() {
		return this.board;
	}
	
	public Point[] getXes() {
		
	}
	
	public Point[] getYs() {
		
	}
	
	
	public void wipeBoard() {
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					board[i][j][k].value = 0; //adjust so that points property of currentvalue is correct
	
				}
			}
		}
	}
	
	//minor change

}
