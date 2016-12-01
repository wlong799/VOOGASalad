package game_player_menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game_object.core.*;
import serializing.*;
import game_player.GamePlayManager;
import game_player.GamePlayer;
import game_player.ISceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author samuelcurtis
 *This class is responsible for displaying the initial GUI Menu and switching between scenes 
 *from this menu to various games that may be played.
 */
public class GamePlayMenu implements IMenuInputListener {
	private List<ItemDescription> myMenuItems;
	private MenuSceneGenerator myMenuSceneGenerator;
	private Marshaller mySerializer;
	private ISceneManager myManager;
	
	public GamePlayMenu(Stage s, ISceneManager gamePlayManager){
		myManager = gamePlayManager;
		mySerializer = new Marshaller();
		getItemDescriptions();
		myMenuSceneGenerator = new HBoxMenu(this,s);
		showMenu(s, myMenuSceneGenerator.getMenuScene(myMenuItems));
	}

	private void showMenu(Stage s, Scene menuScene) {
		s.setScene(menuScene);
		s.show();	
	}

	private void getItemDescriptions() {
		myMenuItems = new ArrayList<ItemDescription>();
		//myMenuItems.add(new ItemDescription("test2", "TestDescription", "blue_snail.png"));
		//myMenuItems.add(new ItemDescription("test2", "TestDescription", "blue_snail.png"));
		//myMenuItems.add(new ItemDescription("test2", "TestDescription", "blue_snail.png"));
		//myMenuItems.add(new ItemDescription("test2", "TestDescription", "blue_snail.png"));
	}

	@Override
	public void itemChosen(String name) {
		try {
			Game game = mySerializer.loadGame(name);
			myManager.playGame(game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void playGame(File f) {
			Game game = mySerializer.loadGameFromFile(f);
			myManager.playGame(game);
	}
	
}
