package game_player;

public interface IHighScoreTracker {
	
	public void saveScore();
	
	public void displayScoreHistory();
	
	public void clearScoreHistory();
}
