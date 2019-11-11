package ourTicTacToe;

public class Point {
	int state; //
	float util_val;
	int num_wins;
	int num_games;
	int placeholder;
	
	//s is either -1, 0 or 1 (-1: O; 0: blank; 1: X)
	public Point(int s){
		state = s;
		util_val = 0;
		num_wins = 0;
		num_games = 1;
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
//		util_val = num_wins/num_games;
	}	

	public int getNumWins(){
		return num_wins;
	}
	public int getNumGames(){
		return num_games;
	}
	public float getUtilValue(){
		if (num_games > 1) {
			return (float) num_wins/(num_games-1);
		} else {
			return (float) num_wins/num_games;
		}
//		return (float) num_wins/num_games;
	}
}
