package ourTicTacToe;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Solver {
	public boolean isP1 = true;
	public ArrayList<Integer> posns = new ArrayList<Integer>(3);
	
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
		Solver game = new Solver();
		
		//RUN THE TRIALS
		/************************* FIRST TRIAL ***********************/
		for(int i=0; i<trials1; i++){
			int while_num = 0;
			while(while_num == 0){
				game.posns = game.nextMoveRandomizationAdjusted(board3D, trials1, i);
				while_num = board3D.updateBoard(game.isP1, game.posns.get(0), game.posns.get(1), game.posns.get(2));
				game.isP1 = !game.isP1;
				
			}
			if (while_num==1){ //Player 1 WINS (X's)
				for(Point p : board3D.getXes()){
					p.updateValue(1);
				}
				for(Point p : board3D.getOs()){
					p.updateValue(-1);
				}
			} else if(while_num==-1){ //Player 2 WINS (O's)
				for(Point p : board3D.getOs()){
					p.updateValue(1);
				}
				for(Point p : board3D.getXes()){
					p.updateValue(-1);
				}
			} else if(while_num==2){ //DRAW
				for(Point p : board3D.getOs()){
					p.updateValue(-1);
				}
				for(Point p : board3D.getXes()){
					p.updateValue(-1);
				}
			}
//			board3D.printBoard();
//			board3D.printBoardState();
//			if(while_num==1){
//				System.out.println("PLAYER 1 WON");
//			}else if(while_num==-1){
//				System.out.println("PLAYER 2 WON");
//			}else if(while_num==2){
//				System.out.println("DRAW");
//			}
			if (i==trials1-1) {
				board3D.printBoard();
				board3D.wipeBoardWipeUtility();
			}
			board3D.wipeBoard();
			
		}
		
		
		/****************** SECOND TRIAL ***************************/
		game.isP1 = true;
		for(int i=0; i<trials2; i++){
			int while_num = 0;
			while(while_num == 0){
				game.posns = game.nextMoveRandomizationAdjusted(board3D, trials1, i);
				while_num = board3D.updateBoard(game.isP1, game.posns.get(0), game.posns.get(1), game.posns.get(2));
				game.isP1 = !game.isP1;
				
			}
			if (while_num==1){ //Player 1 WINS (X's)
				for(Point p : board3D.getXes()){
					p.updateValue(1);
				}
				for(Point p : board3D.getOs()){
					p.updateValue(-1);
				}
			} else if(while_num==-1){ //Player 2 WINS (O's)
				for(Point p : board3D.getOs()){
					p.updateValue(1);
				}
				for(Point p : board3D.getXes()){
					p.updateValue(-1);
				}
			} else if(while_num==2){ //DRAW
				for(Point p : board3D.getOs()){
					p.updateValue(-1);
				}
				for(Point p : board3D.getXes()){
					p.updateValue(-1);
				}
			}

			if (i==trials2-1) {
				board3D.printBoard();
				board3D.wipeBoardWipeUtility();
			}
			
			board3D.wipeBoard();

		}
		
		/****************** THIRD TRIAL ***************************/
		game.isP1 = true;
		for(int i=0; i<trials3; i++){
			int while_num = 0;
			while(while_num == 0){
				game.posns = game.nextMoveRandomizationAdjusted(board3D, trials1, i);
				while_num = board3D.updateBoard(game.isP1, game.posns.get(0), game.posns.get(1), game.posns.get(2));
				game.isP1 = !game.isP1;
				
			}
			if (while_num==1){ //Player 1 WINS (X's)
				for(Point p : board3D.getXes()){
					p.updateValue(1);
				}
				for(Point p : board3D.getOs()){
					p.updateValue(-1);
				}
			} else if(while_num==-1){ //Player 2 WINS (O's)
				for(Point p : board3D.getOs()){
					p.updateValue(1);
				}
				for(Point p : board3D.getXes()){
					p.updateValue(-1);
				}
			} else if(while_num==2){ //DRAW
				for(Point p : board3D.getOs()){
					p.updateValue(-1);
				}
				for(Point p : board3D.getXes()){
					p.updateValue(-1);
				}
			}
			
			if (i==trials3-1) {
				board3D.printBoard();
				board3D.wipeBoardWipeUtility();
			}
			
			board3D.wipeBoard();

		}
	
		//print out a board with utility values after a number of trials (trials1, trials2, trials3)
		
	}
	
	public ArrayList<Integer> nextMoveNoRandom(Point[][][] board){
		ArrayList<Integer> positions = new ArrayList<Integer>(3);
		ArrayList<Point> pointsWithHighUtil = new ArrayList<Point>();
		positions.add(0);
		positions.add(0);
		positions.add(0);
		double highestUtil = -1;
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) {
					if (board[i][j][k].getUtilValue() > highestUtil && board[i][j][k].state == 0){
						highestUtil = board[i][j][k].getUtilValue();
						pointsWithHighUtil.clear();
						pointsWithHighUtil.add(board[i][j][k]);

					} else if (board[i][j][k].getUtilValue() == highestUtil && board[i][j][k].state == 0) {
						pointsWithHighUtil.add(board[i][j][k]);
					}
				}
			}
		}	
		//randomly select one point from arrayList of points and return its position
		Random r = new Random();
		Point selectedPoint = pointsWithHighUtil.get(r.nextInt(pointsWithHighUtil.size()));
		
		positions.set(0, selectedPoint.getI());
		positions.set(1, selectedPoint.getJ());
		positions.set(2, selectedPoint.getK());
		
		return positions;
	}
	
	
	public ArrayList<Integer> nextMoveRandomizationAdjusted(Board3D myBoard, int totalNumTrials, int currentNumTrials) {
		double explorationLikelihood = (currentNumTrials)/totalNumTrials;
		if (Math.random() < explorationLikelihood) {
			return nextMoveNoRandom(myBoard.getBoard());
		} else {
			Random r = new Random();
			
			Point randomizedBlankPoint = myBoard.getBlanks().get(r.nextInt(myBoard.getBlanks().size()));
			
			ArrayList<Integer> myPointNums = new ArrayList<Integer>();
			
			myPointNums.add(randomizedBlankPoint.getI());
			myPointNums.add(randomizedBlankPoint.getJ());
			myPointNums.add(randomizedBlankPoint.getK());
			
			return myPointNums;
		}	
	}
}