package game_player_menu;

import java.io.File;

public interface IMenuInputListener {
	
	void itemChosen(ItemDescription item);
	
	void playGame(File f);
	
	void loadGame(File f);
}
