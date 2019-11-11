package ourTicTacToe;

public class Point {
	int state; //
	float util_val;
	int num_wins;
	int num_games;
	int placeholder;
	
	public Point(int s){
		state = s;
		util_val = 0;
		num_wins = 0;
		num_games = 0;
	}
	
	//Change the state of the Point (-1: O; 0: blank; 1: X)
	public void changeState(int newState){
		state = newState;
	}
	
	//Increase or decrease the value depending on game outcome
	public float updateValue(int outcome){
		if (outcome == 1){	//Part of the winning solution
			util_val += 0.05;
		} else if (outcome == -1){ //Not part of the winning solution
			util_val -= 0.05; 
		}
		return util_val;
	}	

	public int getNumWins(){
		return 0;
	}
	public int getNumGames(){
		return 0;
	}
	public int getUtilValue(){
		return num_wins/num_games;
	}
}
