package game_player_menu;

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

public class GamePlayMenu implements IMenuInputListener {
	private List<ItemDescription> myMenuItems;
	private MenuSceneGenerator myMenuSceneGenerator;
	private Marshaller mySerializer;
	private ISceneManager myManager;
	
	public GamePlayMenu(Stage s, ISceneManager gamePlayManager){
		myManager = gamePlayManager;
		mySerializer = new Marshaller();
		getItemDescriptions();
		myMenuSceneGenerator = new HBoxMenu(this);
		showMenu(s, myMenuSceneGenerator.getMenuScene(myMenuItems));
	}

	private void showMenu(Stage s, Scene menuScene) {
		s.setScene(menuScene);
		s.show();	
	}

	private void getItemDescriptions() {
		myMenuItems = new ArrayList<ItemDescription>();
		myMenuItems.add(new ItemDescription("test", "TestDescription", "blue_snail.png"));
		myMenuItems.add(new ItemDescription("test", "TestDescription", "blue_snail.png"));
		myMenuItems.add(new ItemDescription("test", "TestDescription", "blue_snail.png"));
		myMenuItems.add(new ItemDescription("test", "TestDescription", "blue_snail.png"));
	}

	@Override
	public void itemChosen(String name) {
		try {
			System.out.println("test");
			Game game = mySerializer.loadGame("file:data/game/" + name + ".xml");
			myManager.playGame(game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
