package game_player_menu;

import java.io.File;

public interface IMenuInputListener {
	void itemChosen(String s);
	
	void playGame(File f);
}
