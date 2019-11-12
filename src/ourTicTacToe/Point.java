package ourTicTacToe;

import java.util.ArrayList;

public class Point {
	int state; 
	int num_wins;
	int num_games;
	int placeholder;
	int i_pos;
	int j_pos;
	int k_pos;
	
	//s is either -1, 0 or 1 (-1: O; 0: blank; 1: X)
	public Point(int s, int i, int j, int k){
		state = s;
		num_wins = 0;
		num_games = 1;

		i_pos = i;
		j_pos = j;
		k_pos = k;
	}
	
	//Change the state of the Point (-1: O; 0: blank; 1: X)
	public void changeState(int newState){
		state = newState;
	}
	public int getState(){
		return state;
	}
	
	//Increase or decrease the value depending on game outcome
	public void updateValue(int outcome){
		if (outcome==1){ //PART OF WINNING SOLUTION
			num_wins++;
			num_games++;
		}else if(outcome==-1){			//NOT PART OF WINNING SOLUTION
			num_games++;
		}

	}	

	public int getNumWins(){
		return num_wins;
	}
	public int getNumGames(){
		return num_games;
	}
	public double getUtilValue(){
		if (num_games > 1) {
			return (double) num_wins/(num_games-1);
		} else {
			return (double) num_wins/num_games;
		}
	}
	public int getI(){
		return i_pos;
	}
	public int getJ(){
		return j_pos;
	}
	public int getK(){
		return k_pos;
	}
}
