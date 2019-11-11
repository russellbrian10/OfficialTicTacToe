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
		num_wins = 1;
		num_games = 1;
	}
	
	//Change the state of the Point (-1: O; 0: blank; 1: X)
	public void changeState(int newState){
		state = newState;
	}
	
	//Increase or decrease the value depending on game outcome
	public void updateValue(int outcome){
		if (outcome==1){ //PART OF WINNING SOLUTION
			num_wins++;
			num_games++;
		}else{			//NOT PART OF WINNING SOLUTION
			num_games++;
		}
		util_val = num_wins/num_games;
	}	

	public int getNumWins(){
		return 1;
	}
	public int getNumGames(){
		return 1;
	}
	public float getUtilValue(){
		return util_val;
	}
}
